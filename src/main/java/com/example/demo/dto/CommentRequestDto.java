package com.example.demo.dto;

import com.example.demo.domain.Comments.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

    @Getter
    @NoArgsConstructor
    public class CommentRequestDto {
        private String content;
        private String username;

        @Builder
        public CommentRequestDto(String content, String username){
            this.content = content;
            this.username = username;
        }
}
