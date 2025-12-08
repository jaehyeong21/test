package com.example.demo.global.security.token;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenContext {
    private Long userId;

    public TokenContext(Long userId){
        this.userId = userId;
    }

    public TokenContext(){

    }
}
