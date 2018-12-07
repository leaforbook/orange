package com.leaforbook.orange.util;

import com.alibaba.fastjson.JSON;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class SessionUtil {
    
    public static final String LOGIN_CERTIFICATE = "oneofus";
    public static final String LOGIN_CERTIFICATE_PREFIX = "oneofus_";

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 添加会话属性
     * @param key
     * @param userId
     * @param t
     * @param timeout
     * @param <T>
     */
    public <T> void addAttribute(String key,String userId,T t,long timeout) {
        redisTemplate.opsForValue().set(key+"_"+userId, JSON.toJSONString(t));
        if(timeout>0l) {
            redisTemplate.expire(key+"_"+userId,timeout,TimeUnit.SECONDS);
        }
    }

    /**
     * 添加会话属性
     * @param key
     * @param userId
     * @param t
     * @param <T>
     */
    public <T> void addAttribute(String key,String userId,T t) {
        this.addAttribute(key,userId,t,0l);
    }

    /**
     * 删除会话属性
     * @param key
     * @param userId
     * @param <T>
     */
    public <T> void deleteAttribute(String key,String userId) {
        redisTemplate.delete(key+"_"+userId);
    }

    /**
     * 获取会话属性
     * @param key
     * @param userId
     * @param t
     * @param <T>
     * @return
     */
    public <T> T getAttribute(String key,String userId,Class<T> t) {
        String value = redisTemplate.opsForValue().get(key+"_"+userId);
        if(StringUtils.isEmpty(value)) {
            throw new BasicBusinessException(ExceptionEnum.SESSION_ATTRIBUTE_NON_EXISTENT);
        }
        return JSON.parseObject(value, t);
    }

    /**
     * 添加会话
     * @param request
     * @param userInfo
     */
    public void addSession(HttpServletRequest request,UserInfo userInfo) {
        String certificate = this.getCertificate(request);
        redisTemplate.opsForValue().set(LOGIN_CERTIFICATE_PREFIX+certificate, JSON.toJSONString(userInfo));
        redisTemplate.expire(LOGIN_CERTIFICATE_PREFIX+certificate,60*60*3l, TimeUnit.SECONDS);
    }

    /**
     * 添加会话
     * @param certificate
     * @param userInfo
     */
    public void addSession(String certificate,UserInfo userInfo) {
        redisTemplate.opsForValue().set(LOGIN_CERTIFICATE_PREFIX+certificate, JSON.toJSONString(userInfo));
        redisTemplate.expire(LOGIN_CERTIFICATE_PREFIX+certificate,60*60*3l, TimeUnit.SECONDS);
    }

    /**
     * 删除会话
     * @param request
     */
    public void deleteSession(HttpServletRequest request) {
        String certificate = this.getCertificate(request);
        redisTemplate.delete(LOGIN_CERTIFICATE_PREFIX+certificate);
    }

    /**
     * 删除会话
     * @param certificate
     */
    public void deleteSession(String certificate) {
        redisTemplate.delete(LOGIN_CERTIFICATE_PREFIX+certificate);
    }

    /**
     * 获取会话
     * @param request
     * @return
     */
    public UserInfo getSessionInfo(HttpServletRequest request) {
        String certificate = this.getCertificate(request);
        return this.getSessionInfo(certificate);
    }

    /**
     * 获取会话
     * @param certificate
     * @return
     */
    public UserInfo getSessionInfo(String certificate) {

        String value = redisTemplate.opsForValue().get(LOGIN_CERTIFICATE_PREFIX+certificate);
        if(StringUtils.isBlank(value)) {
            throw new BasicBusinessException(ExceptionEnum.LOGIN_EXPIRE);
        }

        return JSON.parseObject(value,UserInfo.class);
    }

    /**
     * 延长会话
     * @param request
     */
    public void prolongSession(HttpServletRequest request) {
        String certificate = this.getCertificate(request);
        this.prolongSession(certificate);
    }

    /**
     * 延长会话
     * @param certificate
     */
    public void prolongSession(String certificate) {
        long time = redisTemplate.getExpire(LOGIN_CERTIFICATE_PREFIX+certificate, TimeUnit.SECONDS);
        if(time<5*60l) {
            redisTemplate.expire(LOGIN_CERTIFICATE_PREFIX+certificate,time+10*60l, TimeUnit.SECONDS);
        }
    }

    public String getCertificate(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String certificate = null;
        if(cookies!=null&&cookies.length>0) {
            for(Cookie c:cookies) {
                if(LOGIN_CERTIFICATE.equalsIgnoreCase(c.getName())) {
                    certificate = c.getValue();
                }
            }
        }

        if(certificate==null) {
            certificate = request.getHeader("Authorization");
        }

        if(certificate==null) {
            throw new BasicBusinessException(ExceptionEnum.UNLOGIN);
        }

        return certificate;
    }

    public boolean isLogined(HttpServletRequest request) {

        String certificate = this.getCertificate(request);

        if(StringUtils.isBlank(certificate)) {
            return false;
        }
        UserInfo userInfo = null;
        try{
            userInfo = this.getSessionInfo(certificate);
        } finally {
            if(userInfo==null) {
                return false;
            }
        }

        return true;
    }

}
