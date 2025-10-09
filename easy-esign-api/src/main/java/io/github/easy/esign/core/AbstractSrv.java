package io.github.easy.esign.core;

import io.github.easy.esign.error.ESignException;
import io.github.easy.esign.log.Logger;
import io.github.easy.esign.log.LoggerFactory;

public abstract class AbstractSrv {
    private final static Logger logger = LoggerFactory.getLogger(AbstractSrv.class);

    protected Execute execute() {
        Execute execute = ContextHolder.getContext();
        if (execute == null) {
            execute = ESignManager.defaultExecute();
        }
        if (execute == null) {
            throw new ESignException("Can't find execute, because @SwitchApp.name is null or empty string");
        }
        logger.debug("Current esign app: " + execute.getAppName());
        return execute;
    }
}
