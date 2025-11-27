package com.example.demo.global.security.interceptor;


import com.example.demo.global.security.token.TokenContextHolder;
import com.example.demo.global.security.token.TokenManager;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.LinkedHashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    private final Set<ExcludePath> excludePaths = new LinkedHashSet<>();

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception{
        String token = request.getHeader("x-auth-token");

        for (ExcludePath excludePath : excludePaths) {
            if (excludePath.matches(request)) {
                return true;
            }
        }

        if(token == null || token.isBlank()){
            throw new RuntimeException("토큰이 필요합니다");
        }
        tokenManager.validateToken(token);

        return true;
    }

    @PostConstruct
    public void initExcludePaths() {
        excludePaths.add(new ExcludePath("/api/post", HttpMethod.GET));
        excludePaths.add(new ExcludePath("/api/post/{postId}/comments", HttpMethod.GET));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TokenContextHolder.clear();
    }

    @RequiredArgsConstructor
    private static class ExcludePath{
        private final String pathPattern;
        private final HttpMethod method;

        public boolean matches(HttpServletRequest request) {
            return method.matches(request.getMethod()) && new AntPathMatcher().match(pathPattern, request.getRequestURI());
        }
    }
}
