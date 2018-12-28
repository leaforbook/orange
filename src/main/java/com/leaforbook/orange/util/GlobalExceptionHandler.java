package com.leaforbook.orange.util;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public BasicResponse handlerBindException(MethodArgumentNotValidException exception) {

        StringBuffer msg = new StringBuffer();
        exception.getBindingResult().getAllErrors().forEach(x -> msg.append(x.getDefaultMessage()).append("|"));

        return new BasicResponse("666",msg.toString());
    }
}
