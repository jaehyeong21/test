package com.example.demo.service;

import com.example.demo.controller.FollowController;
import com.example.demo.global.security.token.TokenContext;
import com.example.demo.global.security.token.TokenContextHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ExtendWith(MockitoExtension.class)
public class FollowControllerTest {

    @InjectMocks
    FollowController followController;

    @Mock
    FollowService followService;

    //실제 서버 띄우지 않고  Controller를 가짜로 호출할 수 있는 테스트 도구
    private MockMvc mockMvc;

    // 테스트 시작 전에 MockMvc 초기화
    // standaloneSetUp : FollowController 단 하나만 올려서 테스트 한은 방식
    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(followController).build();
    }
    
    @DisplayName("팔로우 성공")
    @Test
    void followSucess() throws Exception {
        // given 준비단계
        // followService.follow() 가 아무 일도 없이 정상 호출된다고 가정
        doNothing().when(followService).follow(anyLong(), anyLong());

        TokenContextHolder.setContext(new TokenContext(1L));
        // when
        ResultActions resultActions = mockMvc.perform(post("/api/users/2/follow"));

        // then
        resultActions.andExpect(status().isOk());

        verify(followService).follow(1L, 2L);
    }

    @DisplayName("팔로우 실패")
    @Test
    void followFail() throws Exception {
        doThrow(new IllegalArgumentException("유효하지 않은 사용자"))
                .when(followService)
                .follow(anyLong(), anyLong());

        TokenContextHolder.setContext(new TokenContext(1L));

        ResultActions resultActions = mockMvc.perform(post("/api/users/2/follow"));

        resultActions.andExpect((status().isBadRequest()));
    }
}
