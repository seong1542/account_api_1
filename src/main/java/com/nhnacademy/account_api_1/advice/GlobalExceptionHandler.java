package com.nhnacademy.account_api_1.advice;

import com.nhnacademy.account_api_1.exception.AlreadyUserException;
import com.nhnacademy.account_api_1.exception.NotFoundDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundDataException.class)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundDataException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AlreadyUserException.class)
    public ResponseEntity<String> alreadyDataExceptionHandler(AlreadyUserException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
