package io.github.easy.esign.log.impl;

import io.github.easy.esign.log.Logger;
import io.github.easy.esign.utils.StrUtil;

public class SystemLogger implements Logger {

    private final String className;

    public SystemLogger(Class<?> clazz) {
        this.className = clazz.getCanonicalName();
    }

    // 是否启用彩色日志
    public static final boolean COLOR_ENABLE;

    static {
        String os = System.getProperty("os.name").toLowerCase();
        COLOR_ENABLE = os.contains("linux") || os.contains("mac");
    }

    // 日志前缀
    private static final String DEBUG_PREFIX = "[DEBUG]: ";
    private static final String INFO_PREFIX = "[INFO]: ";
    private static final String WARN_PREFIX = "[WARN]: ";
    private static final String ERROR_PREFIX = "[ERROR]: ";

    // ANSI 颜色
    private static final String DEBUG_COLOR = "\033[34m";
    private static final String INFO_COLOR = "\033[32m";
    private static final String WARN_COLOR = "\033[33m";
    private static final String ERROR_COLOR = "\033[31m";
    private static final String DEFAULT_COLOR = "\033[39m";

    @Override
    public void debug(String message) {
        println(DEBUG_COLOR, DEBUG_PREFIX, message);
    }

    @Override
    public void info(String message) {
        println(INFO_COLOR, INFO_PREFIX, message);
    }

    @Override
    public void warn(String message) {
        println(WARN_COLOR, WARN_PREFIX, message);
    }

    @Override
    public void error(String message) {
        println(ERROR_COLOR, ERROR_PREFIX, message);
    }

    @Override
    public void debug(String str, Object... args) {
        println(DEBUG_COLOR, DEBUG_PREFIX, str, args);
    }

    @Override
    public void info(String str, Object... args) {
        println(INFO_COLOR, INFO_PREFIX, str, args);
    }

    @Override
    public void warn(String str, Object... args) {
        println(WARN_COLOR, WARN_PREFIX, str, args);
    }

    @Override
    public void error(String str, Object... args) {
        println(ERROR_COLOR, ERROR_PREFIX, str, args);
    }

    private void println(String color, String prefix, String str, Object... args) {
        String msg = className + " " + prefix + StrUtil.format(str, args);
        if (COLOR_ENABLE) {
            System.out.println(color + msg + DEFAULT_COLOR);
        } else {
            System.out.println(msg);
        }
    }
}
