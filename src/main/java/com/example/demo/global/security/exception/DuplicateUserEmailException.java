package com.example.demo.global.security.exception;

public class DuplicateUserEmailException extends RuntimeException{
    public DuplicateUserEmailException(){
        super("이미 사용중인 이메일입니다.");
    }

    public DuplicateUserEmailException(String message){
        super(message);
    }
}
