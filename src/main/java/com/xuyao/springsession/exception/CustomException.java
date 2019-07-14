package com.xuyao.springsession.exception;

public class CustomException extends Exception{
    private String code;
    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
