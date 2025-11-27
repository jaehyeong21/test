package com.example.demo.controller;

import com.example.demo.dto.CommentRequestDto;
import com.example.demo.dto.CommentResponseDto;
import com.example.demo.global.security.token.TokenContextHolder;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentResponseDto> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

    @PostMapping
    public ResponseEntity<?> createComment(@PathVariable Long postId,
                                           @RequestBody CommentRequestDto dto) {
        Long userId = TokenContextHolder.getContext().getUserId();
        commentService.createComment(userId, postId, dto);
        return ResponseEntity.ok("댓글 등록 완료");
    }
}
