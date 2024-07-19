package io.github.easy.esign.log.impl;


import io.github.easy.esign.log.Logger;

public final class Slf4JLogger implements Logger {
    private final org.slf4j.Logger logger;

    public Slf4JLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void debug(String format, Object... arg) {
        logger.debug(String.format(format, arg));
    }

    @Override
    public void info(String format, Object... arg) {
        logger.info(String.format(format, arg));
    }

    @Override
    public void warn(String format, Object... arg) {
        logger.warn(String.format(format, arg));
    }

    @Override
    public void error(String format, Object... arg) {
        logger.error(String.format(format, arg));
    }
}
