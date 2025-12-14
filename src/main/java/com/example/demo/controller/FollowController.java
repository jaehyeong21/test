package com.example.demo.controller;

import com.example.demo.dto.FollowerDto;
import com.example.demo.dto.FollowingDto;
import com.example.demo.global.security.token.TokenContextHolder;
import com.example.demo.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("{targetUserId}/follow")
    public ResponseEntity<?> follow (@PathVariable Long targetUserId){
        Long me = TokenContextHolder.getContext().getUserId();
        followService.follow(me, targetUserId);
        return ResponseEntity.ok("팔로우 성공");
    }

    @DeleteMapping("{targetUserId}/follow")
    public ResponseEntity<?> unfollow (@PathVariable Long targetUserId){
        Long me = TokenContextHolder.getContext().getUserId();
        followService.unfollow(me, targetUserId);
        return ResponseEntity.ok("언팔로우 성공");
    }

    @GetMapping("{userId}/following")
    public Page<FollowingDto> getFollowing(@PathVariable Long userId,
                                           @RequestParam(defaultValue = "0") int page){
        return followService.getFollowing(userId, page);
    }

    @GetMapping("{userId}/followers")
    public Page<FollowerDto> getFollower(@PathVariable Long userId,
                                            @RequestParam(defaultValue = "0") int page){
        return followService.getFollower(userId, page);
    }
}
