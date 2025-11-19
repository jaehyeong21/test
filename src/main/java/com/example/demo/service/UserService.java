package com.example.demo.service;

import com.example.demo.domain.users.User;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.global.security.exception.DuplicateUserEmailException;
import com.example.demo.global.security.exception.InvalidUserEmailException;
import com.example.demo.global.security.exception.InvalidUserPasswordException;
import com.example.demo.global.security.token.TokenDto;
import com.example.demo.global.security.token.TokenManager;
import com.example.demo.global.security.token.TokenResponseDto;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenManager tokenManager;

    @Transactional
    public TokenResponseDto register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new DuplicateUserEmailException();
        }
        User user = registerDto.toEntity();
        userRepository.save(user);
        return toTokenResponseDto(user);
    }

    @Transactional(readOnly = true)
    public TokenResponseDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new InvalidUserEmailException());

        if (!user.checkPassword(loginDto.getPassword())) {
            throw new InvalidUserPasswordException();
        }
        return toTokenResponseDto(user);
    }

    private TokenResponseDto toTokenResponseDto(User user) {

        TokenDto tokenDto = new TokenDto(user.getId());
        return tokenManager.generateToken(tokenDto);
    }
}
