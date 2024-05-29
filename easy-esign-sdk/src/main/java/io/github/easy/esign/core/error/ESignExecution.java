package io.github.easy.esign.core.error;

import java.io.Serializable;

public class ESignExecution extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public ESignExecution() {
        super();
    }

    public ESignExecution(String message) {
        super(message);
    }

    public ESignExecution(String message, Throwable cause) {
        super(message, cause);
    }

    public ESignExecution(Throwable cause) {
        super(cause);
    }

    protected ESignExecution(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
