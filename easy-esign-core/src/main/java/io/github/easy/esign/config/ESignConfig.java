package io.github.easy.esign.config;

import lombok.Data;

@Data
public class ESignConfig {
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
}
