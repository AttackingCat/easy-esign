package io.github.easy.esign.struct.auth.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrgAuthResp {

    //机构认证授权长链接（有效期30天）
    //【注】支持自定义域名，微信小程序H5内嵌场景需要使用长链接
    private String authUrl;
    //机构认证授权短链接 （有效期30天）
    private String authShortUrl;
    //本次认证授权流程ID（开发者请注意保管流程ID，可用于【查询认证授权流程详情】）
    private String authFlowId;

}
