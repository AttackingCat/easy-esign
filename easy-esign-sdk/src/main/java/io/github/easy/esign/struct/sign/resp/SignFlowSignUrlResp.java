package io.github.easy.esign.struct.sign.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * https://open.esign.cn/doc/opendoc/pdf-sign3/pvfkwd
 * <p>
 * 获取签署页面链接
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignFlowSignUrlResp {

    /**
     * 签署短链接（有效期180天）
     */
    String shortUrl;

    /**
     * 签署长链接（永久有效）
     * <p>
     * 【注】支持自定义域名，微信小程序H5内嵌场景需要使用长链接
     */
    String url;

}
