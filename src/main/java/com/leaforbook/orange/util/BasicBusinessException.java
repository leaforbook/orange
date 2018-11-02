package com.leaforbook.orange.util;

public class BasicBusinessException extends RuntimeException {

    public BasicBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicBusinessException(String code,String message, Throwable cause) {
        super(code+":"+message, cause);
    }

    public BasicBusinessException(String code,String message) {
        super(code+":"+message);
    }

    public BasicBusinessException(ExceptionEnum exception) {
        super(exception.getCode()+":"+exception.getMessage());
    }

    public BasicBusinessException(ExceptionEnum exception,Throwable cause) {
        super(exception.getCode()+":"+exception.getMessage(),cause);
    }

}
