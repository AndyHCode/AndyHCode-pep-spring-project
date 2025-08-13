package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String UsernameAlreadyExistsHandler(UsernameAlreadyExistsException e){
        return e.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalArgumentHandler(IllegalArgumentException e){
        return e.getMessage();
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String userNotFoundHandler(UserNotFoundException e){
        return e.getMessage();
    }

    
}
