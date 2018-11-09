package com.leaforbook.orange.util;

import com.leaforbook.orange.common.service.UserRoleService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HasRoleInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private UserRoleService userRoleService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod method = (HandlerMethod)handler;
        HasRole hasRole = method.getMethodAnnotation(HasRole.class);

        if(hasRole!=null) {
            String sufValue = request.getParameter(hasRole.sufKey());
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

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
