package com.muratkistan.spring_security_6_jwt.error;

import com.muratkistan.spring_security_6_jwt.exception.InvalidTokenException;
import com.muratkistan.spring_security_6_jwt.exception.NotFoundException;
import com.muratkistan.spring_security_6_jwt.exception.NotUniqueUsernameException;
import com.muratkistan.spring_security_6_jwt.shared.Messages;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            NotUniqueUsernameException.class,
            InvalidTokenException.class,
            NotFoundException.class,
            AuthenticationException.class,
    })
    ResponseEntity<ApiError> handleException(Exception exception, HttpServletRequest request){
        ApiError apiError = new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        if(exception instanceof MethodArgumentNotValidException) {
            String message = Messages.getMessageForLocale("app.error.validation", LocaleContextHolder.getLocale());
            apiError.setMessage(message);
            apiError.setStatus(400);
            var validationErrors = ((MethodArgumentNotValidException)exception).getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacing) -> existing));
            apiError.setValidationErrors(validationErrors);
        } else if (exception instanceof NotUniqueUsernameException) {
            apiError.setStatus(400);
            apiError.setValidationErrors(((NotUniqueUsernameException)exception).getValidationErrors());
        } else if (exception instanceof InvalidTokenException) {
            apiError.setStatus(400);
        } else if (exception instanceof AuthenticationException) {
            apiError.setStatus(401);
        }

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

}