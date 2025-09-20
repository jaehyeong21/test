package com.example.demo.service;

import com.example.demo.domain.users.User;
import com.example.demo.dto.RegisterDto;
import com.example.demo.global.security.exception.DuplicateUserEmailException;
import com.example.demo.global.security.token.TokenDto;
import com.example.demo.global.security.token.TokenManager;
import com.example.demo.global.security.token.TokenResponseDto;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        User user = userRepository.save(registerDto.toEntity());

        return toTokenResponseDto(user);
    }
    private TokenResponseDto toTokenResponseDto(User user) {
        TokenDto tokenDto = TokenDto.builder()
                .userId(user.getId())
                .build();

        return tokenManager.generateToken(tokenDto);
    }
}
