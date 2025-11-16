package com.example.demo.controller;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.global.security.token.TokenContextHolder;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final CommentService commentService;
    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto postRequestDto){
        Long userId = TokenContextHolder.getContext().getUserId();
        postService.createPost(userId, postRequestDto);
        return ResponseEntity.ok("게시물 등록 완료");
    }

    @GetMapping("/all")
    public List<PostRequestDto> getPosts(){
        return postService.getPosts();
    }

}
