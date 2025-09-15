package com.example.demo.global.security.token;

import lombok.Builder;

public class TokenResponseDto {
    private String token;

    @Builder
    public TokenResponseDto(String token){
        this.token = token;
    }

}
