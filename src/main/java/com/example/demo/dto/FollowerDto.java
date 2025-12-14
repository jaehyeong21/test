package com.example.demo.dto;

import com.example.demo.domain.follows.Follow;
import lombok.Getter;

@Getter
public class FollowerDto {
    private Long userId;
    private String userName;

    public FollowerDto(Follow follow){
        this.userId = follow.getFollower().getId();
        this.userName = follow.getFollower().getName();
    }

    public FollowerDto(Long userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }
}
