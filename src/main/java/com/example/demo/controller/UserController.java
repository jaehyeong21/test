package com.example.demo.controller;

import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final PostService postService;

    @GetMapping("/{userId}/posts")
    public ResponseEntity<?> getUserPosts(@PathVariable Long userId){
        return ResponseEntity.ok(postService.getPostsByUser(userId));
    }
}
