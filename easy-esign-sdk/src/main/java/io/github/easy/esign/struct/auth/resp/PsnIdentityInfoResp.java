package io.github.easy.esign.struct.auth.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.easy.esign.struct.commen.PsnInfo;

/**
 * https://open.esign.cn/doc/opendoc/auth3/vssvtu
 * <p>
 * 查询个人认证信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PsnIdentityInfoResp {

    /**
     * 用户在e签宝的实名认证状态
     * <p>
     * 0 - 未实名，1 - 已实名
     */
    Integer realnameStatus;

    /**
     * 是否授权相关信息给当前应用
     * <p>
     * true - 已授权，false - 未授权
     */
    Boolean authorizeUserInfo;

    /**
     * 个人账号ID
     */
    String psnId;

    /**
     * 个人用户身份信息
     */
    PsnAccount psnAccount;
    /**
     * 个人用户身份信息
     */
    PsnInfo psnInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PsnAccount {
        String accountMobile;
        String accountEmail;
    }
}
