package com.muratkistan.spring_security_6_jwt.exception;


import com.muratkistan.spring_security_6_jwt.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collections;
import java.util.Map;

public class NotUniqueUsernameException extends RuntimeException {


    public NotUniqueUsernameException(String message) {
        super(message);
    }

    public Map<String, String> getValidationErrors(){
        return Collections.singletonMap("email", Messages.getMessageForLocale("app.constraint.username.notunique", LocaleContextHolder.getLocale()));
    }



}