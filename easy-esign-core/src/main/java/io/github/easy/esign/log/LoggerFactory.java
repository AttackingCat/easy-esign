package io.github.easy.esign.log;

import io.github.easy.esign.log.impl.Slf4JLogger;
import io.github.easy.esign.log.impl.SystemLogger;
import org.slf4j.helpers.NOPLogger;

import java.util.concurrent.ConcurrentHashMap;

public class LoggerFactory {

    private static final ConcurrentHashMap<String, Logger> loggers = new ConcurrentHashMap<>();

    public static Logger getLogger(Class<?> clazz) {
        String className = clazz.getCanonicalName();
        Logger logger = loggers.get(className);
        if (logger == null) {
            try {
                org.slf4j.Logger slf4JLogger = org.slf4j.LoggerFactory.getLogger(clazz);
                if (slf4JLogger instanceof NOPLogger) {
                    System.err.println("No log implementation found for sl4j !");
                }
                loggers.put(className, new Slf4JLogger(slf4JLogger));
            } catch (Exception e) {
                loggers.put(className, new SystemLogger(clazz));
            }
        }
        return loggers.get(className);
    }
}
