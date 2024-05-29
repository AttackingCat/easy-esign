package io.github.easy.esign.core.log;

public interface Logger {

    void debug(String message);

    void info(String message);

    void warn(String message);

    void error(String message);

    void debug(String format, Object... arg);

    void info(String format, Object... arg);

    void warn(String format, Object... arg);

    void error(String format, Object... arg);
}
