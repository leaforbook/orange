package com.leaforbook.orange.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class BasicExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {

        BasicResponse response = new BasicResponse();

        if(e instanceof BasicBusinessException) {
            BasicBusinessException bbe = (BasicBusinessException)e;
            response.setCode(bbe.getCode());
            response.setMsg(bbe.getMessage());
            log.debug("业务异常详情：",e);
        } else {
            response.setCode("999999");
            response.setMsg("系统错误，请联系管理员: 240688248@qq.com");
            log.error("系统异常详情",e);
        }

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Cache-Control", "no-cache, must-revalidate");

        try {
            httpServletResponse.getWriter().write(JSON.toJSONString(response));
        } catch (IOException e1) {
            log.error("异常返回错误：",e1);
        }

        return new ModelAndView();
    }

}
