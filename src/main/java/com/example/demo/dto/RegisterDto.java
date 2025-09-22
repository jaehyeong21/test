package com.example.demo.dto;

import com.example.demo.domain.users.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private String userId;

    public User toEntity(){
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .userId(this.userId)
                .createdAt(LocalDate.now())
                .build();
    }
}
