package com.example.demo.global.security.exception;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
