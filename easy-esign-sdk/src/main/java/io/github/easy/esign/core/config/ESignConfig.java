package io.github.easy.esign.core.config;

import lombok.Data;

@Data
public class ESignConfig {

    /**
     * appId
     */
    String appId;

    /**
     * 密钥
     */
    String secret;

    /**
     * 开启沙箱模式
     */
    Boolean sandbox = false;

    /**
     * 配置懒加载，第一次调用才构建HttpClient
     */
    Boolean lazyInit = false;

    /**
     * 打印banner
     */
    Boolean printBanner = true;
}
