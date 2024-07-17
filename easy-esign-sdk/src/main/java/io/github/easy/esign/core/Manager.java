package io.github.easy.esign.core;

import io.github.easy.esign.core.api.*;
import io.github.easy.esign.core.config.ConfigFactory;
import io.github.easy.esign.core.config.ESignConfig;
import io.github.easy.esign.core.config.ESignConfigs;
import io.github.easy.esign.core.error.ESignException;
import io.github.easy.esign.core.log.Logger;
import io.github.easy.esign.core.log.LoggerFactory;
import io.github.easy.esign.utils.StrUtil;

public class Manager {
    /**
     * 全局配置对象
     */
    public static volatile ESignConfig config;

    private static volatile Execute execute;

    private final static Logger logger = LoggerFactory.getLogger(Manager.class);

    private static final Class<?>[] executeLoggers = {
            DocTemplateSrv.class,
            FileSrv.class,
            OrgAuthSrv.class,
            OrgSealSrv.class,
            PsnAuthSrv.class,
            SignFlowSrv.class,
    };

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

    public static void setConfigs(ESignConfigs cfg) {

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
                    Manager.config = (ConfigFactory.createConfig());
                }
            }
        }
        return config;
    }

    public static Execute getExecute() {
        if (execute == null) {
            synchronized (Manager.class) {
                execute = new Execute(config);
            }
        }
        return execute;
    }

    private static void setExecuteLogger() {
        for (Class<?> clazz : executeLoggers) {
            Execute.setLog(clazz);
        }
    }

    private static boolean configCheck(ESignConfig config) {
        if (config == null) {
            return false;
        } else {
            return config.getAppId() != null && config.getSecret() != null;
        }
    }
}
