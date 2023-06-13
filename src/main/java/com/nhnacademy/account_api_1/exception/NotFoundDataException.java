package com.nhnacademy.account_api_1.exception;

public class NotFoundDataException extends IllegalArgumentException{
    public NotFoundDataException(String m){
        super(m);
    }
}
