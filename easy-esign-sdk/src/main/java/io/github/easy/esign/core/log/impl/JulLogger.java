package io.github.easy.esign.core.log.impl;

import io.github.easy.esign.core.log.Logger;

import java.util.logging.Level;

public final class JulLogger implements Logger {

    private final java.util.logging.Logger logger;

    public JulLogger(java.util.logging.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void debug(String message) {
        logger.log(Level.FINE, message);
    }

    @Override
    public void info(String message) {
        logger.log(Level.INFO, message);
    }

    @Override
    public void warn(String message) {
        logger.log(Level.WARNING, message);
    }

    @Override
    public void error(String message) {
        logger.log(Level.SEVERE, message);
    }

    @Override
    public void debug(String format, Object... arg) {
        logger.log(Level.FINE, String.format(format, arg));
    }

    @Override
    public void info(String format, Object... arg) {
        logger.log(Level.INFO, String.format(format, arg));
    }

    @Override
    public void warn(String format, Object... arg) {
        logger.log(Level.WARNING, String.format(format, arg));
    }

    @Override
    public void error(String format, Object... arg) {
        logger.log(Level.SEVERE, String.format(format, arg));
    }
}
