package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
  private String content;
  private String username;

    @Builder
    public CommentResponseDto(String content, String username){
        this.content = content;
        this.username = username;
    }
}
