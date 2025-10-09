package io.github.easy.esign.log.impl;

import io.github.easy.esign.log.Logger;
import io.github.easy.esign.utils.StrUtil;

import java.lang.reflect.Method;

public class Log4jLogger implements Logger {

    private final Object logger;
    private final Method debugMethod, infoMethod, warnMethod, errorMethod;

    private Log4jLogger(Object logger, Method debugMethod, Method infoMethod,
                        Method warnMethod, Method errorMethod) {
        this.logger = logger;
        this.debugMethod = debugMethod;
        this.infoMethod = infoMethod;
        this.warnMethod = warnMethod;
        this.errorMethod = errorMethod;
    }

    public static Logger create(Class<?> clazz) {
        try {
            Class<?> log4jClass = Class.forName("org.apache.log4j.Logger");
            Object log4jLogger = log4jClass.getMethod("getLogger", Class.class).invoke(null, clazz);
            Method debugMethod = log4jClass.getMethod("debug", Object.class);
            Method infoMethod = log4jClass.getMethod("info", Object.class);
            Method warnMethod = log4jClass.getMethod("warn", Object.class);
            Method errorMethod = log4jClass.getMethod("error", Object.class);
            return new Log4jLogger(log4jLogger, debugMethod, infoMethod, warnMethod, errorMethod);
        } catch (Throwable e) {
            return null;
        }
    }

    // ---------- helper ----------
    private String withCaller(String message) {
        return "[" + findCaller() + "] " + message;
    }

    private String findCaller() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (StackTraceElement e : stack) {
            String cls = e.getClassName();
            if (!cls.startsWith("io.github.easy.esign.log.impl") &&
                    !cls.startsWith("java.") &&
                    !cls.startsWith("jdk.")) {
                // 模仿 log4j %l 的格式：full.class.method(file:line)
                String file = e.getFileName() == null ? "UnknownSource" : e.getFileName();
                int line = e.getLineNumber();
                return cls + "." + e.getMethodName() + "(" + file + ":" + line + ")";
            }
        }
        return "unknown";
    }

    // ---------- log methods ----------
    @Override
    public void debug(String message) {
        invoke(debugMethod, withCaller(message));
    }

    @Override
    public void info(String message) {
        invoke(infoMethod, withCaller(message));
    }

    @Override
    public void warn(String message) {
        invoke(warnMethod, withCaller(message));
    }

    @Override
    public void error(String message) {
        invoke(errorMethod, withCaller(message));
    }

    @Override
    public void debug(String format, Object... arg) {
        debug(StrUtil.format(format, arg));
    }

    @Override
    public void info(String format, Object... arg) {
        info(StrUtil.format(format, arg));
    }

    @Override
    public void warn(String format, Object... arg) {
        warn(StrUtil.format(format, arg));
    }

    @Override
    public void error(String format, Object... arg) {
        error(StrUtil.format(format, arg));
    }

    private void invoke(Method method, String msg) {
        try {
            method.invoke(logger, msg);
        } catch (Throwable ignored) {
        }
    }
}
