package com.muratkistan.spring_security_6_jwt.exception;

public class InvalidTokenException extends  RuntimeException{

    public InvalidTokenException(String message) {
        super(message);
    }
}
