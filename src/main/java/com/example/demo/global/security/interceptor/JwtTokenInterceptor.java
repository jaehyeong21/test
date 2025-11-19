package com.example.demo.global.security.interceptor;


import com.example.demo.global.security.token.TokenContextHolder;
import com.example.demo.global.security.token.TokenManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception{
        String token = request.getHeader("x-auth-token");

        if(token == null || token.isBlank()){
            throw new RuntimeException("토큰이 필요합니다");
        }
        tokenManager.validateToken(token);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TokenContextHolder.clear();
    }
}
