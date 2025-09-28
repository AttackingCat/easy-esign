package io.github.easy.esign.struct.auth.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * <a href="https://open.esign.cn/doc/opendoc/auth3/rx8igf">获取个人认证&授权页面链接</a>
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PsnAuthResp {
    String authFlowId;
    String authUrl;
    String authShortUrl;
}
