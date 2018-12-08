package com.leaforbook.orange.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class HasSessionInterceptor implements HandlerInterceptor {
    @Autowired
    private SessionUtil sessionUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod method = null;
        try {
            method = (HandlerMethod)handler;
        } catch (Throwable e) {
            return true;
        }

        HasSession hasSession = method.getMethodAnnotation(HasSession.class);

        Boolean flag = null;

        if(hasSession!=null) {
            UserInfo userInfo = sessionUtil.getSessionInfo(request);
            flag = sessionUtil.getAttribute(hasSession.key(),userInfo.getUserId(),Boolean.class);
            if(flag==null||!flag) {
                throw new BasicBusinessException(ExceptionEnum.SESSION_ATTRIBUTE_NON_EXISTENT);
            }
            sessionUtil.deleteAttribute(hasSession.key(),userInfo.getUserId());
        }

        return true;
    }
}
