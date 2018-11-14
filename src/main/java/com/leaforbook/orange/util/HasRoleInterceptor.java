package com.leaforbook.orange.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leaforbook.orange.common.service.UserRoleService;
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
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@Slf4j
public class HasRoleInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private UserRoleService userRoleService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod method = (HandlerMethod)handler;
        HasRole hasRole = method.getMethodAnnotation(HasRole.class);

        if(hasRole!=null) {
            String sufValue = null;
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String body = this.getBody(request);
                JSONObject json = JSON.parseObject(body);
                sufValue = json.getString(hasRole.sufKey());
            } else if("GET".equalsIgnoreCase(request.getMethod())) {
                sufValue = request.getParameter(hasRole.sufKey());
            }

            if(StringUtils.isEmpty(sufValue)) {
                throw new BasicBusinessException(ExceptionEnum.PARAMETERS_NOT_ENOUGH);
            }
            String roleId = hasRole.preKey()+sufValue;
            UserInfo userInfo = sessionUtil.getSessionInfo(request);
            boolean flag = userRoleService.hasRole(userInfo.getUserId(),roleId);
            if(!flag) {
                throw new BasicBusinessException(ExceptionEnum.HAS_NO_ROLE);
            }
        }

        return true;
    }


    private String getBody(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod()))
        {
            try {
                return request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            } catch (IOException e) {
                log.error("获取请求Body失败",e);
            }
        }

        return null;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info(JSON.toJSONString(modelAndView));
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
