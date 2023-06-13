package com.nhnacademy.account_api_1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyUserException extends IllegalArgumentException{
    public AlreadyUserException(String m){
        super(m);
    }
}
