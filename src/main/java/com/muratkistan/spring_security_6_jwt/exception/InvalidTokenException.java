package com.muratkistan.spring_security_6_jwt.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.muratkistan.spring_security_6_jwt.shared.Messages;

public class InvalidTokenException extends  RuntimeException{

       public InvalidTokenException(){
        super(Messages.getMessageForLocale("app.user.invalid.token", LocaleContextHolder.getLocale()));
    }
}
