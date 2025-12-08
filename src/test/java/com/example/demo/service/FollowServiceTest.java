package com.example.demo.service;
import com.example.demo.domain.users.User;
import com.example.demo.repository.FollowRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("팔로우 기능 테스트")
@Transactional
@ExtendWith(MockitoExtension.class)
public class FollowServiceTest {

    @InjectMocks
    FollowService followService;

    @Mock
    UserRepository userRepository;

    @Mock
    FollowRepository followRepository;

    @Test
    void followTest() {
        // given
        User me = userRepository.save(new User("me@test.com", "1234"));
        User target = userRepository.save(new User("you@test.com", "5678"));

        when(userRepository.findById(1L)).thenReturn(Optional.of(me));
        when(userRepository.findById(2L)).thenReturn(Optional.of(target));
        when(followRepository.existsByFollowerAndFollowing(me, target)).thenReturn(false);
        // when
        followService.follow(me.getId(), target.getId());

        // then
        boolean result = followRepository.existsByFollowerAndFollowing(me, target);
        assertThat(result).isTrue();
    }
}
