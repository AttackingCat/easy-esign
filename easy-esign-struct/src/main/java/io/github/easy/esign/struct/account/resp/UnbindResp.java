package io.github.easy.esign.struct.account.resp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnbindResp {
    /**
     * 账号解绑长链接（永久有效）
     * 【注】能获取到解绑链接不代表目前一定有绑定e签宝SaaS账号。如果当前账号没有绑定证件，会在获取验证码后提示：“该账号未绑定身份信息，无需解绑”。
     */
    private String accountUnbindUrl;

    /**
     * 账号解绑短链接 （有效期30天）
     */
    private String accountUnbindShortUrl;
}
