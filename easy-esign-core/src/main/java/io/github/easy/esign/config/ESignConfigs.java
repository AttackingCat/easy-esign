package io.github.easy.esign.config;

import lombok.Data;

import java.util.List;

@Data
public class ESignConfigs {

    /**
     * 默认配置名称
     */
    String defaultConfigName = "default";

    /**
     * 打印banner
     */
    Boolean printBanner = true;

    /**
     * 配置集合
     */
    List<ESignConfig> configs;
}
