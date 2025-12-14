package com.example.demo.service;

import com.example.demo.domain.follows.Follow;
import com.example.demo.domain.users.User;
import com.example.demo.dto.FollowerDto;
import com.example.demo.dto.FollowingDto;
import com.example.demo.global.security.exception.NotFoundException;
import com.example.demo.repository.FollowRepository;
import com.example.demo.repository.FollowRepositoryCustom;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final FollowRepositoryCustom followRepositoryCustom;

    @Transactional
    public void follow(Long me, Long targetUserId) {
        if (followRepository.existsByFollowerIdAndFollowingId(me, targetUserId)) {
            throw new RuntimeException("이미 팔로우 했습니다");
        }

        User meUser = userRepository.getReferenceById(me);
        User targetUser = userRepository.findById(targetUserId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자입니다."));

        followRepository.save(new Follow(meUser, targetUser));
        //
    }

    @Transactional
    public void unfollow(Long me , Long targetUserId){
        Follow follow = followRepository.findByFollowerIdAndFollowingId(me, targetUserId)
                .orElseThrow(() -> new NotFoundException("팔로우 기록이 없습니다"));

        followRepository.delete(follow);
    }

    @Transactional(readOnly = true)
    public Page<FollowingDto> getFollowing(Long userId, int page){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("유저없음"));

        Pageable pageable = PageRequest.of(page, 10);

        return followRepositoryCustom.findFollowingDtos(userId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<FollowerDto> getFollower(Long userId, int page){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("유저없음"));

        Pageable pageable = PageRequest.of(page, 10);

        return followRepositoryCustom.findFollowerDtos(userId, pageable);
    }
}
