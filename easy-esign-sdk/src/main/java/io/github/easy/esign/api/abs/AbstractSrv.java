package io.github.easy.esign.api.abs;

import io.github.easy.esign.core.ContextHolder;
import io.github.easy.esign.core.Execute;
import io.github.easy.esign.core.error.ESignException;
import io.github.easy.esign.core.log.Logger;
import io.github.easy.esign.core.log.LoggerFactory;

import static io.github.easy.esign.core.ESignManager.defaultExecute;

public abstract class AbstractSrv {
    private final static Logger logger = LoggerFactory.getLogger(AbstractSrv.class);

    protected Execute execute() {
        Execute execute = ContextHolder.getContext();
        if (execute == null) {
            execute = defaultExecute();
        }
        if (execute == null) {
            throw new ESignException("Can't find execute, because @SwitchApp.name is null or empty string");
        }
        logger.info("Switch execute: " + execute.getAppName());
        return execute;
    }
}
