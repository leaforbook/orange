package com.leaforbook.orange.common.auth;

import com.leaforbook.orange.common.dict.UserConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CertificateUtils {
    public static String getCertificate(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        String certificate = null;
        if(cookies!=null&&cookies.length>0) {
            for(Cookie c:cookies) {
                if(UserConstants.LOGIN_CERTIFICATE.equalsIgnoreCase(c.getName())) {
                    certificate = c.getValue();
                }
            }
        }

        return certificate;
    }
}
