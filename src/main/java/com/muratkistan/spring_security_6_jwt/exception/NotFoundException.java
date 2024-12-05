package com.muratkistan.spring_security_6_jwt.exception;

public class NotFoundException extends  RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}
