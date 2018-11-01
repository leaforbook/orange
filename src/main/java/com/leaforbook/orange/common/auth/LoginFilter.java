package com.leaforbook.orange.common.auth;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String certificate = this.getCertificate(httpServletRequest);



    }

    private String getCertificate(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        String certificate = null;
        for(Cookie c:cookies) {
            if("".equalsIgnoreCase(c.getName())) {
                certificate = c.getValue();
            }
        }
        return certificate;
    }

}
