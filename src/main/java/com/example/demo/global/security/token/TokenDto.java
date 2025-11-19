package com.example.demo.global.security.token;

import lombok.Getter;

@Getter
public class TokenDto {
    private Long userId;

    public TokenDto(Long userId){
        this.userId = userId;
    }
}
