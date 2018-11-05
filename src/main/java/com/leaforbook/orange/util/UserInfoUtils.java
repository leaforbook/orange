package com.leaforbook.orange.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class UserInfoUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public UserInfo getUserInfo(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String certificate = null;
        if(cookies!=null&&cookies.length>0) {
            for(Cookie c:cookies) {
                if("oneofus".equalsIgnoreCase(c.getName())) {
                    certificate = c.getValue();
                }
            }
        }

        return JSON.parseObject(redisTemplate.opsForValue().get("oneofus"+"_"+certificate),UserInfo.class);
    }

}
