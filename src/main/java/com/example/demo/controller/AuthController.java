package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.global.security.token.TokenResponseDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping
    public TokenResponseDto login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }

}
