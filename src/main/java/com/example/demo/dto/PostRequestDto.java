package com.example.demo.dto;

import com.example.demo.domain.Posts.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {
    private String title;
    private String content;
    private String userId;

    public PostRequestDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUser().getUserId();
    }
}
