package io.github.easy.esign.log;

import io.github.easy.esign.log.impl.Log4jLogger;
import io.github.easy.esign.log.impl.Slf4JLogger;
import io.github.easy.esign.log.impl.SystemLogger;

import java.util.concurrent.ConcurrentHashMap;

public class LoggerFactory {

    private static final ConcurrentHashMap<String, Logger> loggers = new ConcurrentHashMap<>();

    public static Logger getLogger(Class<?> clazz) {
        String className = clazz.getCanonicalName();
        return loggers.computeIfAbsent(className, name -> createLogger(clazz));
    }

    private static Logger createLogger(Class<?> clazz) {
        // 尝试 Log4j（反射）
        Logger logger = Log4jLogger.create(clazz);
        if (logger != null) return logger;

        // 尝试 SLF4J
        try {
            return new Slf4JLogger(clazz);
        } catch (Throwable ignored) {
        }

        // 兜底 System.out
        return new SystemLogger(clazz);
    }
}
