package com.example.demo.global.security.token;



public class TokenContextHolder {

    private static final ThreadLocal<TokenContext> contextHolder =
            ThreadLocal.withInitial(TokenContext::new);

    public static TokenContext getContext() {
        return contextHolder.get();
    }

    public static void setContext(TokenContext context) {
        contextHolder.set(context);
    }

    public static void clear() {
        contextHolder.remove();
    }
}