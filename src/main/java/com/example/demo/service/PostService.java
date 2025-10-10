package com.example.demo.service;

import com.example.demo.domain.Posts.Post;
import com.example.demo.domain.users.User;
import com.example.demo.dto.PostRequestDto;
import com.example.demo.global.security.exception.BadRequestException;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @Transactional
    public PostRequestDto createPost(Long userId, PostRequestDto postRequestDto){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(("존재하지 않는 유저")));

        Post post = Post.builder()
                .user(user)
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .createdAt(LocalDate.now())
                .build();

        postRepository.save(post);

        return postRequestDto;

    }
}
