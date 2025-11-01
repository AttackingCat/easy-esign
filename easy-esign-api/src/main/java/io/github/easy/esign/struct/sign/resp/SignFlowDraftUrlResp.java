package io.github.easy.esign.struct.sign.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * https://open.esign.cn/doc/opendoc/file-and-template3/gu4n9g6fcaenhmnw
 * <p>
 * 获取填写页面链接 响应参数
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignFlowDraftUrlResp {

    /**
     * 长链地址（需要登录长链接永久有效，免登录链接30分钟有效）
     * <p>
     * 【注】小程序H5内嵌场景需要使用长链接，支持自定义域名（需要指定clientType=H5 时才会返回自定义域名填写链接）
     */
    String draftUrl;

    /**
     * 短链地址（需要登录短链接30天有效，免登录链接30分钟有效）
     */
    String draftShortUrl;

}
