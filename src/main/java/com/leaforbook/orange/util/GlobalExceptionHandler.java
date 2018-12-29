package com.leaforbook.orange.util;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public BasicResponse handlerBindException(MethodArgumentNotValidException exception) {

        StringBuffer msg = new StringBuffer();
        msg.append(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());

        return new BasicResponse("666",msg.toString());
    }
}
