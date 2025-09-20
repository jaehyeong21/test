package com.example.demo.dto;

import com.example.demo.domain.users.User;
import lombok.Getter;

@Getter
public class RegisterDto {
    private String name;
    private String email;
    private String password;

    public User toEntity(){
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
