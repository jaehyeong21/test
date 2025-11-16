package com.example.demo.controller;

import com.example.demo.global.security.token.TokenContextHolder;
import com.example.demo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/post/{postId}/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<?> toggleLike(@PathVariable Long postId){
        Long userId = TokenContextHolder.getContext().getUserId();

        boolean liked = likeService.toggleLike(userId, postId);

        return ResponseEntity.ok().body(
                Map.of("liked", liked)
        );
    }
}
