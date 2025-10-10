package com.example.demo.controller;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.global.security.token.TokenContextHolder;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto postRequestDto){
        Long userId = TokenContextHolder.getContext().getUserId();
        postService.createPost(userId, postRequestDto);
        return ResponseEntity.ok("게시물 등록 완료");
    }
}
