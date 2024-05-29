package io.github.easy.esign.core;

import io.github.easy.esign.api.BaseHandler;
import io.github.easy.esign.core.config.ESignConfig;
import io.github.easy.esign.core.config.ESignConfigFactory;
import io.github.easy.esign.core.error.ESignExecution;
import io.github.easy.esign.utils.StrUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class ESignManager {
    /**
     * 全局配置对象
     */
    public static volatile ESignConfig config;

    private final static ThreadLocal<BaseHandler> serviceContext = new ThreadLocal<>();

    private final static ConcurrentHashMap<String, Object> services = new ConcurrentHashMap<>();

    public static void setConfig(ESignConfig config) {
        ESignManager.config = config;
        // 打印 banner
        if (config != null && config.getPrintBanner()) {
            StrUtil.printEasyEsign();
        }
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            config = null;
            serviceContext.remove();
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

    public static BaseHandler getContext() {
        if (serviceContext.get() == null) {
            synchronized (ESignManager.class) {
                if (serviceContext.get() == null) {
                    serviceContext.set(new BaseHandler(config));
                }
            }
        }
        return serviceContext.get();
    }

    /**
     * 获取service，非spring环境下使用
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> clazz) {
        String name = clazz.getPackage().getName();
        if (!"io.github.easy.esign.api".equals(name)) {
            throw new ESignExecution("can not get service from class " + clazz);
        }
        String canonicalName = clazz.getCanonicalName();
        if (services.get(canonicalName) != null) {
            return (T) services.get(canonicalName);
        }
        for (Method method : clazz.getMethods()) {
            if ("getInstance".equals(method.getName())) {
                try {
                    T invoke = (T) method.invoke(null);
                    services.put(canonicalName, invoke);
                    return invoke;
                } catch (Exception e) {
                    throw new ESignExecution(e);
                }

            }
        }
        throw new ESignExecution("can not find constructor" + clazz.getName());
    }
}
