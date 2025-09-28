package io.github.easy.esign.log.impl;

import io.github.easy.esign.log.Logger;
import io.github.easy.esign.utils.StrUtil;
import org.slf4j.LoggerFactory;

public class Slf4JLogger implements Logger {

    private final org.slf4j.Logger logger; // 直接用全类名

    public Slf4JLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
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
        if (logger.isDebugEnabled()) logger.debug(StrUtil.format(format, arg));
    }

    @Override
    public void info(String format, Object... arg) {
        if (logger.isInfoEnabled()) logger.info(StrUtil.format(format, arg));
    }

    @Override
    public void warn(String format, Object... arg) {
        if (logger.isWarnEnabled()) logger.warn(StrUtil.format(format, arg));
    }

    @Override
    public void error(String format, Object... arg) {
        if (logger.isErrorEnabled()) logger.error(StrUtil.format(format, arg));
    }
}
