package io.github.easy.esign.core;

import io.github.easy.esign.config.ESignConfig;
import io.github.easy.esign.config.ESignConfigs;
import io.github.easy.esign.error.ESignException;
import io.github.easy.esign.log.Logger;
import io.github.easy.esign.log.LoggerFactory;
import io.github.easy.esign.utils.StrUtil;
import lombok.Synchronized;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ESignManager {

    private static final Logger logger = LoggerFactory.getLogger(ESignManager.class);

    private static ESignConfigs configs;

    // appName -> Execute 实例
    private static final Map<String, Execute> executeMap = new HashMap<>();

    static {
        // JVM 关闭时，异步关闭所有 Execute
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                executeMap.values().forEach(execute -> CompletableFuture.runAsync(execute::close))
        ));
    }

    /**
     * 热更新配置
     */
    @Synchronized
    public static void loadConfigs(ESignConfigs configs) {
        // 构建新 Execute Map
        Map<String, Execute> newExecuteMap = buildExecuteMap(configs);

        // 保存旧实例
        Map<String, Execute> oldMap = new HashMap<>(executeMap);

        // 替换 Map 和 configs
        executeMap.clear();
        executeMap.putAll(newExecuteMap);
        ESignManager.configs = configs;

        // 异步关闭旧实例，避免阻塞主线程
        oldMap.values().forEach(execute -> CompletableFuture.runAsync(execute::close));

        logger.info("ESignManager loadConfigs completed");
    }

    public static void loadConfig(ESignConfig config) {
        ESignConfigs eSignConfigs = new ESignConfigs();
        List<ESignConfig> objects = new ArrayList<>();
        objects.add(config);
        eSignConfigs.setConfigs(objects);
        loadConfigs(eSignConfigs);
    }

    /**
     * 获取默认 Execute
     */
    public static Execute defaultExecute() {
        if (executeMap.isEmpty()) {
            throw new ESignException("ESign app not init");
        }
        return executeMap.get(configs.getDefaultConfigName());
    }

    /**
     * 根据 appName 切换上下文
     */
    public static void switchExecute(String appName) {
        Execute execute = executeMap.get(appName);
        if (execute == null) {
            throw new ESignException("ESign app not found: " + appName);
        }
        ContextHolder.setContext(execute);
    }

    public static void clearExecute() {
        ContextHolder.clearContext();
    }

    public static Boolean getAutoExplanation() {
        return configs.getAutoExplanation();
    }

    /**
     * 校验配置合法性
     */
    private static void validateConfigs(ESignConfigs configs) {
        if (configs == null || configs.getConfigs() == null || configs.getConfigs().isEmpty()) {
            throw new ESignException("ESign configuration is empty");
        }
        for (ESignConfig cfg : configs.getConfigs()) {
            if (!configCheck(cfg)) {
                throw new ESignException("Invalid config: " + cfg.getName());
            }
        }
    }

    /**
     * 构建 Execute Map 并设置默认名称和代理
     */
    private static Map<String, Execute> buildExecuteMap(ESignConfigs configs) {
        validateConfigs(configs);
        Map<String, Execute> map = new HashMap<>();
        for (ESignConfig cfg : configs.getConfigs()) {
            if (StrUtil.isBlank(cfg.getName())) {
                cfg.setName(UUID.randomUUID().toString().replace("-", "").toUpperCase());
            }
            if (map.containsKey(cfg.getName())) {
                throw new ESignException("Duplicate appName: " + cfg.getName());
            }
            if (cfg.getProxy() == null) {
                cfg.setProxy(configs.getProxy());
            }
            map.put(cfg.getName(), new Execute(cfg));
        }

        // 设置默认配置名
        if (configs.getConfigs().size() == 1) {
            ESignConfig cfg = configs.getConfigs().get(0);
            configs.setDefaultConfigName(cfg.getName());
        }
        return map;
    }

    /**
     * 配置合法性检查
     */
    private static boolean configCheck(ESignConfig cfg) {
        return cfg != null && cfg.getAppId() != null && cfg.getSecret() != null;
    }
}
