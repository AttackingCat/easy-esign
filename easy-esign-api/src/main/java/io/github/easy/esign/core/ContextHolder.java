package io.github.easy.esign.core;

public class ContextHolder {
    private static final ThreadLocal<Execute> contextHolder = new ThreadLocal<>();

    static void setContext(Execute context) {
        contextHolder.set(context);
    }

    public static Execute getContext() {
        return contextHolder.get();
    }

    static void clearContext() {
        contextHolder.remove();
    }
}
