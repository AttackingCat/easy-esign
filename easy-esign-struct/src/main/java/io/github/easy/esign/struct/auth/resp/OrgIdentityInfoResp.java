package io.github.easy.esign.struct.auth.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.easy.esign.struct.common.OrgInfo;

/**
 * https://open.esign.cn/doc/opendoc/auth3/xxz4tc
 * <p>
 * 查询个人认证信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrgIdentityInfoResp {

    /**
     * 实名认证状态
     * <p>
     * 0 - 未实名，1 - 已实名
     */
    Integer realnameStatus;

    /**
     * 是否授权身份信息给当前应用
     * <p>
     * true - 已授权，false - 未授权
     */
    Boolean authorizeUserInfo;

    /**
     * 机构账号ID
     */
    String orgId;

    /**
     * 机构名称
     */
    String orgName;
    /**
     * 机构认证信息
     */
    OrgInfo orgInfo;
}
