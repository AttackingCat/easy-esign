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

    public static void setConfig(ESignConfig config) {
        Manager.config = config;

        if (configCheck(config)) {
            Manager.config = null;
            if (configCheck(getConfig())) {
                String msg = "Configuration file not found,please check your config file,appId and secret must be not null!";
                logger.error(msg);
                throw new ESignException(msg);
            }
            if (config.getPrintBanner()) {
                // 打印 banner
                StrUtil.printEasyEsign();
            }
            logger.info("ESign AppId: %s", Manager.config.getAppId());
            logger.info("ESign SandBox: %s", Manager.config.getSandbox());
            logger.info("Log use: %s", logger.getClass());
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
            return true;
        } else return config.getAppId() == null || config.getSecret() == null;
    }
}
