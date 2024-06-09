package io.github.easy.esign.core;

import io.github.easy.esign.core.config.ESignConfig;
import io.github.easy.esign.core.config.ESignConfigFactory;
import io.github.easy.esign.core.error.ESignException;
import io.github.easy.esign.core.log.Logger;
import io.github.easy.esign.core.log.LoggerFactory;
import io.github.easy.esign.utils.StrUtil;

import java.util.concurrent.ConcurrentHashMap;

public class ESignManager {
    /**
     * 全局配置对象
     */
    public static volatile ESignConfig config;

    private static ConcurrentHashMap<String, BaseExecute> executes = new ConcurrentHashMap<>();

    private final static Logger logger = LoggerFactory.getLogger(ESignManager.class);

    public static void setConfig(ESignConfig config) {
        ESignManager.config = config;

        if (configCheck(config)) {
            ESignManager.config = null;
            if (configCheck(getConfig())) {
                String msg = "Configuration file not found,please check your config file,appId and secret must be not null!";
                logger.error(msg);
                throw new ESignException(msg);
            }
            if (config.getPrintBanner()) {
                // 打印 banner
                StrUtil.printEasyEsign();
            }
            logger.info("ESign AppId: %s", ESignManager.config.getAppId());
            logger.info("ESign SandBox: %s", ESignManager.config.getSandbox());
            logger.info("Log use: %s", logger.getClass());
        }
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            config = null;
            for (BaseExecute value : executes.values()) {
                value.close();
            }
            executes = null;
        }));
    }

    public static ESignConfig getConfig() {
        if (config == null) {
            synchronized (ESignManager.class) {
                if (config == null) {
                    ESignManager.config = (ESignConfigFactory.createConfig());
                }
            }
        }
        return config;
    }

    public static BaseExecute getContext(Class<?> clazz) {
        String key = clazz.getName();
        BaseExecute serviceContext = executes.get(key);
        if (serviceContext == null) {
            synchronized (ESignManager.class) {
                serviceContext = new BaseExecute(config, clazz);
                executes.put(key, serviceContext);
            }
        }
        return serviceContext;
    }

    private static boolean configCheck(ESignConfig config) {
        if (config == null) {
            return true;
        } else return config.getAppId() == null || config.getSecret() == null;
    }
}
