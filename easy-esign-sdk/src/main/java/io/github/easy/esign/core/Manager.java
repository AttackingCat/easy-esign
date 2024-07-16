package io.github.easy.esign.core;

import io.github.easy.esign.core.config.ESignConfig;
import io.github.easy.esign.core.config.ESignConfigFactory;
import io.github.easy.esign.core.error.ESignException;
import io.github.easy.esign.core.log.Logger;
import io.github.easy.esign.core.log.LoggerFactory;
import io.github.easy.esign.utils.StrUtil;

public class Manager {
    /**
     * 全局配置对象
     */
    public static volatile ESignConfig config;

    private static volatile BaseExecute execute;

    private final static Logger logger = LoggerFactory.getLogger(Manager.class);

    public static void setConfig(ESignConfig cfg) {
        config = cfg;
        if (configCheck(cfg)) {
            logger.info("ESign AppId: %s", config.getAppId());
            logger.info("ESign SandBox: %s", config.getSandbox());
            logger.info("Log use: %s", logger.getClass());
        } else if (!configCheck(getConfig())) {
            throw new ESignException("Configuration file not found,please check your config file,appId and secret must be not null!");
        }
        if (config.getPrintBanner()) {
            // 打印 banner
            StrUtil.printEasyESign();
        }
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            execute.close();
            config = null;
            execute = null;
        }));
    }

    public static ESignConfig getConfig() {
        if (config == null) {
            synchronized (Manager.class) {
                if (config == null) {
                    Manager.config = (ESignConfigFactory.createConfig());
                }
            }
        }
        return config;
    }

    public static BaseExecute getExecute(Class<?> clazz) {
        if (execute == null) {
            synchronized (Manager.class) {
                execute = new BaseExecute(config);
            }
        }
        execute.setLog(clazz);
        return execute;
    }

    private static boolean configCheck(ESignConfig config) {
        if (config == null) {
            return false;
        } else {
            return config.getAppId() != null && config.getSecret() != null;
        }
    }
}
