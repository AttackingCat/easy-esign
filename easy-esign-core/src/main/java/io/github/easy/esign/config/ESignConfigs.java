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
     * 是否自动解释错误，要求可以访问该域名 tapi.esign.cn
     */
    Boolean autoExplanation = true;

    /**
     * 代理
     */
    Proxy proxy;

    /**
     * 配置集合
     */
    List<ESignConfig> configs;
}
