package io.github.easy.esign.core.config;

import io.github.easy.esign.core.error.ESignExecution;
import kotlin.reflect.KVariance;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class ESignConfigFactory {

    public static String configPath = "esign-v3.properties";

    public static ESignConfig createConfig() {
        return createConfig(configPath);
    }

    public static ESignConfig createConfig(String path) {
        Properties properties = readProperties(path);
        return parser(properties);
    }

    private static Properties readProperties(String propertiesPath) {
        try {
            InputStream is = ESignConfigFactory.class.getClassLoader().getResourceAsStream(propertiesPath);
            if (is == null) {
                return null;
            }
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        } catch (IOException e) {
            throw new ESignExecution("配置文件(" + propertiesPath + ")加载失败", e);
        }
    }

    private static ESignConfig parser(Properties properties) {
        ESignConfig config = new ESignConfig();
        if (properties == null) {
            return config;
        }
        Optional.ofNullable(properties.get("esign-v3.app-id")).ifPresent(appId -> {
            config.setAppId((String) appId);
        });
        Optional.ofNullable(properties.get("esign-v3.secret")).ifPresent(secret -> {
            config.setSecret((String) secret);
        });
        Optional.ofNullable(properties.get("esign-v3.sandbox")).ifPresent(sandbox -> {
            config.setSandbox(Boolean.valueOf(sandbox.toString()));
        });
        Optional.ofNullable(properties.get("esign-v3.lazyInit")).ifPresent(lazyInit -> {
            config.setLazyInit(Boolean.valueOf(lazyInit.toString()));
        });
        Optional.ofNullable(properties.get("esign-v3.printBanner")).ifPresent(printBanner -> {
            config.setPrintBanner(Boolean.valueOf(printBanner.toString()));
        });
        Map<String, String> callbackUrl = new HashMap<>();
        properties.forEach((k, v) -> {
            String str = k.toString();
            String prefix = "esign-v3.callback-url.";
            if (str.contains(prefix)) {
                callbackUrl.put(str.replace(prefix, ""), v.toString());
            }
        });
        config.setCallBackUrl(callbackUrl);
        return config;
    }

}
