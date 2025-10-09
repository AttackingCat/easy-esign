package io.github.easy.esign.config;

import lombok.Data;

import java.util.List;

@Data
public class ESignConfigs {

    /**
     * 默认配置名称
     * 非必选，默认default，只有一个app的情况下不用该参数，或者自行实现 {@link io.github.easy.esign.adapter.ESignAppNameAdapter}
     */
    String defaultConfigName;

    /**
     * 打印banner
     * 非必选
     */
    Boolean printBanner = true;

    /**
     * 是否自动解释错误，要求可以访问该域名 tapi.esign.cn
     * 非必选
     */
    Boolean autoExplanation = true;

    /**
     * 代理
     * 非必选，优先使用应用内部设置的代理
     */
    Proxy proxy;

    /**
     * 配置集合
     * 必选
     */
    List<ESignConfig> configs;
}
