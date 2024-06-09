package io.github.easy.esign.core.config;

import lombok.Data;

import java.util.Map;

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
     * 打印banner
     */
    Boolean printBanner = true;

    /**
     * 自定义回调地址
     */
    Map<String,String> callBackUrl;
}
