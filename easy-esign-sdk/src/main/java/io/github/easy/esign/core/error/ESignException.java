package io.github.easy.esign.core.error;

import io.github.easy.esign.core.log.Logger;
import io.github.easy.esign.core.log.LoggerFactory;

import java.io.Serializable;

public class ESignException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;


    private static final Logger log = LoggerFactory.getLogger(ESignException.class);

    public ESignException() {
        super();
    }

    public ESignException(String message) {
        super(message);
        log.error(message);
    }

    public ESignException(String message, Throwable cause) {
        super(message, cause);
        log.error(message);
    }

    public ESignException(Throwable cause) {
        super(cause);
        log.error(cause.getMessage());
    }
}
