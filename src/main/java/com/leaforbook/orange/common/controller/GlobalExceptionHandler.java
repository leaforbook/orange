package com.leaforbook.orange.common.controller;

import com.leaforbook.orange.util.BasicResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public BasicResponse handlerBindException(BindException exception) {

        StringBuffer msg = new StringBuffer();
        exception.getAllErrors().forEach(x -> msg.append(x.getDefaultMessage()).append("|"));

        return new BasicResponse("666",msg.toString());
    }
}
