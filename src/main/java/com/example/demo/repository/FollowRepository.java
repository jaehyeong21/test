package com.example.demo.repository;

import com.example.demo.domain.follows.Follow;
import com.example.demo.domain.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);
    Optional<Follow> findByFollowerIdAndFollowingId(Long follower, Long following);
    List<Follow> findAllByFollower(User follower);
    Page<Follow> findAllByFollowing(User following, Pageable pageable);
    Page<Follow> findAllByFollower(User follower, Pageable pageable);
}
