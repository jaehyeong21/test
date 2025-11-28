package com.example.demo.dto;

import com.example.demo.domain.Follows.Follow;
import lombok.Getter;

@Getter
public class FollowerDto {
    private Long userId;
    private String userName;

    public FollowerDto(Follow follow){
        this.userId = follow.getFollower().getId();
        this.userName = follow.getFollower().getName();
    }
}
