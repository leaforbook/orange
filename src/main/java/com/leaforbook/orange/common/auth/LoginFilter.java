package com.leaforbook.orange.common.auth;

import com.alibaba.fastjson.JSON;
import com.leaforbook.orange.util.RepeatableHttpServletRequest;
import com.leaforbook.orange.util.SessionUtil;
import com.leaforbook.orange.util.UserInfo;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class LoginFilter implements Filter {

    @Autowired
    private SessionUtil sessionUtil;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = new RepeatableHttpServletRequest((HttpServletRequest)servletRequest);
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String certificate = sessionUtil.getCertificate(httpServletRequest);
        boolean flag = isLogined(certificate);
        if(!flag) {
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            try {
                out = httpServletResponse.getWriter();
                out.write(JSON.toJSONString("用户未认证"));
            } catch (IOException e) {
                log.error(e.toString(), e);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
            return;
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

    private boolean isLogined(String certificate) {
        if(StringUtils.isBlank(certificate)) {
            return false;
        }
        UserInfo userInfo = null;
        try{
            userInfo = sessionUtil.getSessionInfo(certificate);
        } finally {
            if(userInfo==null) {
                return false;
            }else {
                sessionUtil.prolongSession(certificate);
            }
        }

        return true;
    }

}
