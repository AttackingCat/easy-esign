package io.github.easy.esign;

import io.github.easy.esign.config.ESignConfig;
import io.github.easy.esign.config.ESignConfigs;
import io.github.easy.esign.error.ESignException;
import io.github.easy.esign.log.Logger;
import io.github.easy.esign.log.LoggerFactory;
import io.github.easy.esign.utils.BannerUtil;
import io.github.easy.esign.utils.StrUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ESignManager {
    /**
     * 全局配置对象
     */
    private static final Map<String, Execute> executeMap = new HashMap<>();

    private static ESignConfigs configs;

    private final static Logger logger = LoggerFactory.getLogger(ESignManager.class);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executeMap.values().forEach(Execute::close);
        }));
    }

    public static Boolean getAutoExplanation() {
        return configs.getAutoExplanation();
    }

    //TODO use private
    public static void setConfigs(ESignConfigs configs) {
        logger.info("Log use: %s", logger.getClass());
        if (configs == null) {
            throw new ESignException("Configuration not found,please check your config file!");
        }
        if (configs.getConfigs() == null || configs.getConfigs().isEmpty()) {
            throw new ESignException("Configuration is empty,please check your config file!");
        }

        List<ESignConfig> appConfigs = configs.getConfigs();

        for (ESignConfig cfg : appConfigs) {
            // Check
            if (StrUtil.isBlank(cfg.getName()) && appConfigs.size() == 1) {
                logger.info("That configuration must hava a name, appId=%s", cfg.getAppId());
            }
            if (!configCheck(cfg)) {
                String msg = String.format("Configuration name: %s init fail! Please cheek your appId and secret", cfg.getName());
                throw new ESignException(msg);
            }
            // Proxy
            if (cfg.getProxy() != null) {
                logger.info("ESign proxy: %s", cfg.getProxy().toString());
            } else if (configs.getProxy() != null) {
                cfg.setProxy(configs.getProxy());
                logger.info("ESign proxy: %s", configs.getProxy().toString());
            }

            // Init and Manager
            executeMap.put(cfg.getName(), initExecute(cfg));

            logger.info("ESign appId: %s", cfg.getAppId());
            logger.info("ESign sandBox: %s", cfg.getSandbox());
            logger.info("ESign app name: {%s} init success! ", cfg.getName());
        }

        if (appConfigs.size() == 1) {
            String name = appConfigs.get(0).getName();
            if (StrUtil.isBlank(name)) {
                name = "Easy ESign";
            }
            configs.setDefaultConfigName(name);
        }

        logger.info("Default Configuration name: %s", configs.getDefaultConfigName());

        if (configs.getPrintBanner()) {
            BannerUtil.printEasyESign();
        }
        ESignManager.configs = configs;
    }

    public static Execute defaultExecute() {
        return executeMap.get(configs.getDefaultConfigName());
    }

    public static void switchExecute(String name) {
        Execute execute;
        if (StrUtil.isNotBlank(name) && executeMap.containsKey(name)) {
            execute = executeMap.get(name);
        } else {
            throw new ESignException("ESign app name: " + name + " not found!");
        }
        ContextHolder.setContext(execute);
    }

    public static void clearExecute() {
        ContextHolder.clearContext();
    }


    private static boolean configCheck(ESignConfig config) {
        if (config == null) {
            return false;
        } else {
            return config.getAppId() != null && config.getSecret() != null;
        }
    }

    private static Execute initExecute(ESignConfig cfg) {
        return new Execute(cfg);
    }
}
