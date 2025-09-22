package com.example.demo.global.security.exception;

public class InvalidUserEmailException extends RuntimeException{
    public InvalidUserEmailException(){
        super("유효하지 않은 아이디입니다.");
    }

    public InvalidUserEmailException(String message){
        super(message);
    }
}
