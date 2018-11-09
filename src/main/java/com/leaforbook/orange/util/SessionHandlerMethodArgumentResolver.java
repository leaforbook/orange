package com.leaforbook.orange.util;

import com.alibaba.fastjson.JSON;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletRequest;

public class SessionHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SessionUtil sessionUtil;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if(methodParameter.hasParameterAnnotation(Session.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        Session session = methodParameter.getParameterAnnotation(Session.class);
        String key = session.key();
        Class type = methodParameter.getParameterType();
        boolean notNull = session.notNull();

        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        UserInfo userInfo = sessionUtil.getUserInfo(request);

        String value = null;
        if(SessionUtil.LOGIN_CERTIFICATE.equals(key)) {
            return userInfo;
        }
        value = redisTemplate.opsForValue().get(key+"_"+userInfo.getUserId());

        if(notNull&&StringUtils.isEmpty(value)) {
            throw new BasicBusinessException(ExceptionEnum.LOGIN_EXPIRE);
        } else if(StringUtils.isEmpty(value)) {
            return null;
        }

        return JSON.parseObject(value,type);
    }

}
