package com.example.demo.dto;

import com.example.demo.domain.Follows.Follow;
import lombok.Getter;

@Getter
public class FollowingDto {
    private Long userId;
    private String userName;

    public FollowingDto(Follow follow){
        this.userId = follow.getFollowing().getId();
        this.userName = follow.getFollowing().getName();
    }
}
