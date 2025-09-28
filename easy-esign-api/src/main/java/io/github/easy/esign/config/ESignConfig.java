package io.github.easy.esign.config;

import lombok.Data;

@Data
public final class ESignConfig {
    String name;
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
     * 代理
     */
    Proxy proxy;

    /**
     * 请求超时时间，重新加载配置最大等待时长
     */
    Integer connectTimeout = 1000 * 20;
}
