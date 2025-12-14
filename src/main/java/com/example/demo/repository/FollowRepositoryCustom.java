package com.example.demo.repository;

import com.example.demo.dto.FollowerDto;
import com.example.demo.dto.FollowingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowRepositoryCustom {
    Page<FollowingDto> findFollowingDtos(Long userId, Pageable pageable);
    Page<FollowerDto> findFollowerDtos(Long userId, Pageable pageable);
}
