package com.example.demo.dto;

import com.example.demo.domain.Comments.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

    @Getter
    @NoArgsConstructor
    public class CommentRequestDto {
        private Long id;
        private String content;
        private String userId;
        private String username;

        public CommentRequestDto(Comment comment){
            this.id = comment.getId();
            this.content = comment.getContent();
            this.userId = comment.getUser().getUserId();
            this.username = comment.getUser().getName();
        }

        @Builder
        public CommentRequestDto(Long id, String content, String userId, String username){
            this.id = id;
            this.content = content;
            this.userId = userId;
            this.username = username;
        }
}
