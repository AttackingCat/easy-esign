package io.github.easy.esign.core.log.impl;

import io.github.easy.esign.core.log.Logger;

public class SystemLogger implements Logger {

    private final String className;

    public SystemLogger(Class clazz) {
        this.className = clazz.getCanonicalName();
    }

    /**
     * 日志输出的前缀
     */
    public static String DEBUG_PREFIX = "[DEBUG]: ";
    public static String INFO_PREFIX = "[INFO]: ";
    public static String WARN_PREFIX = "[WARN]: ";
    public static String ERROR_PREFIX = "[ERROR]: ";

    /**
     * 日志输出的颜色
     */
    public static String DEBUG_COLOR = "\033[34m";
    public static String INFO_COLOR = "\033[32m";
    public static String WARN_COLOR = "\033[33m";
    public static String ERROR_COLOR = "\033[31m";
    public static String DEFAULT_COLOR = "\033[39m";


    @Override
    public void debug(String message) {
        println(DEBUG_COLOR, DEBUG_PREFIX, message);
    }

    @Override
    public void info(String message) {
        println(INFO_COLOR, DEBUG_PREFIX, message);
    }

    @Override
    public void warn(String message) {
        println(WARN_COLOR, DEBUG_PREFIX, message);
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
    public void error(String format, Object... arg) {
        println(ERROR_COLOR, ERROR_PREFIX, format, arg);
    }

    /**
     * 打印日志到控制台
     *
     * @param color  颜色编码
     * @param prefix 前缀
     * @param str    字符串
     * @param args   参数列表
     */
    public void println(String color, String prefix, String str, Object... args) {
        // 彩色日志
        System.out.println(color + className + " " + prefix + String.format(str, args) + DEFAULT_COLOR);
    }
}

