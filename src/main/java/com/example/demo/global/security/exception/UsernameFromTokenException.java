package com.example.demo.global.security.exception;

public class UsernameFromTokenException extends RuntimeException{
    public UsernameFromTokenException(String message){
        super(message);
    }
}

