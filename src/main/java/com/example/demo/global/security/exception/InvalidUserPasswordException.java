package com.example.demo.global.security.exception;

public class InvalidUserPasswordException extends RuntimeException{
    public InvalidUserPasswordException(){
        super("유효하지 않은 비밀번호입니다.");
    }

    public InvalidUserPasswordException(String message){
        super(message);
    }
}
