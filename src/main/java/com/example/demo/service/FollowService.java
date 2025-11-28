package com.example.demo.service;

import com.example.demo.domain.Follows.Follow;
import com.example.demo.domain.users.User;
import com.example.demo.dto.FollowerDto;
import com.example.demo.dto.FollowingDto;
import com.example.demo.repository.FollowRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public void follow(Long me, Long targetUserId){
        User meUser = userRepository.findById(me).orElseThrow();
        User target = userRepository.findById(targetUserId).orElseThrow();

        if (followRepository.existsByFollowerAndFollowing(meUser, target))
            throw new RuntimeException("이미 팔로우 했습니다");

        followRepository.save(new Follow(meUser, target));
    }

    @Transactional
    public void unfollow(Long me , Long targetUserId){
        User meUser = userRepository.findById(me).orElseThrow();
        User target = userRepository.findById(targetUserId).orElseThrow();

        Follow follow = followRepository.findByFollowerAndFollowing(meUser, target)
                .orElseThrow(() -> new RuntimeException("팔로우 기록이 없습니다"));

        followRepository.delete(follow);
    }

    @Transactional(readOnly = true)
    public List<FollowingDto> getFollowing(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저없음"));
        List<Follow> followingList = followRepository.findAllByFollower(user);

        return followRepository.findAllByFollower(user)
                .stream()
                .map(FollowingDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FollowerDto> getFollower(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저없음"));
        List<Follow> followerList = followRepository.findAllByFollowing(user);

        return followRepository.findAllByFollowing(user)
                .stream()
                .map(FollowerDto::new)
                .toList();
    }
}
