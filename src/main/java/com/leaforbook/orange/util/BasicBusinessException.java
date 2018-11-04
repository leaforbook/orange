package com.leaforbook.orange.util;

public class BasicBusinessException extends RuntimeException {

    private String code;
    private String message;

    public BasicBusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BasicBusinessException(String code,String message, Throwable cause) {
        super(code+":"+message, cause);
        this.code = code;
        this.message = message;
    }

    public BasicBusinessException(String code,String message) {
        super(code+":"+message);
        this.code = code;
        this.message = message;
    }

    public BasicBusinessException(ExceptionEnum exception) {
        super(exception.getCode()+":"+exception.getMessage());
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }

    public BasicBusinessException(ExceptionEnum exception,Throwable cause) {
        super(exception.getCode()+":"+exception.getMessage(),cause);
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
