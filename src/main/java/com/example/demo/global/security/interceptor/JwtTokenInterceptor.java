package com.example.demo.global.security.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception{
        String token = request.getHeader("x-auth-token");

        if(token == null || token.isBlank()){
            throw new RuntimeException("토큰이 필요합니다");
        }
        System.out.println("요청 URI = " + request.getRequestURI());

        return true;
    }

}
