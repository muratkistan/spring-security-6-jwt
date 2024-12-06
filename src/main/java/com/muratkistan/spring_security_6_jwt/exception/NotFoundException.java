package com.muratkistan.spring_security_6_jwt.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.muratkistan.spring_security_6_jwt.shared.Messages;

public class NotFoundException extends  RuntimeException{

     public NotFoundException(long id){
        super(Messages.getMessageForLocale("app.user.not.found", LocaleContextHolder.getLocale(), id));
    }
}
