package com.leaforbook.orange.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leaforbook.orange.common.service.CommonResourceService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@Slf4j
public class HasResourceInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private CommonResourceService commonResourceService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod method = (HandlerMethod)handler;
        HasResource hasResource = method.getMethodAnnotation(HasResource.class);

        if(hasResource!=null) {
            String resourceId = null;
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String body = this.getBody(request);
                JSONObject json = JSON.parseObject(body);
                resourceId = json.getString(hasResource.resourceId());
            } else if("GET".equalsIgnoreCase(request.getMethod())) {
                resourceId = request.getParameter(hasResource.resourceId());
            }

            if(StringUtils.isEmpty(resourceId)) {
                throw new BasicBusinessException(ExceptionEnum.PARAMETERS_NOT_ENOUGH);
            }

            UserInfo userInfo = sessionUtil.getSessionInfo(request);
            boolean flag = commonResourceService.hasResource(userInfo.getUserId(),hasResource.resourceType(),resourceId);

            if(!flag) {
                throw new BasicBusinessException(ExceptionEnum.HAS_NO_RESOURCE);
            }
        }

        return true;
    }


    private String getBody(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod()))
        {
            try {
                BufferedReader reader = request.getReader();
                String body = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                reader.close();
                return body;
            } catch (IOException e) {
                log.error("获取请求Body失败",e);
            }
        }

        return null;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
