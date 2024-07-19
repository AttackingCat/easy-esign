package io.github.easy.esign.struct.auth.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.easy.esign.struct.constant.OrgIDCardType;

/**
 * <a href="https://open.esign.cn/doc/opendoc/auth3/vssvtu">...</a>
 * <p>
 * 查询个人认证信息
 * <p>
 * 【注意事项】
 * <p>
 * 入参中psnId、psnAccount和psnIDCardNum三个参数只选择一个传入即可查询个人的认证信息。
 * <p>
 * 查询优先级为 psnId > psnAccount > psnIDCardNum
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrgIdentityInfoReq {

    /**
     */
    String orgId;

    /**
     */
    String orgName;

    /**
     */
    String orgIDCardNum;

    /**
     */
    OrgIDCardType orgIDCardType;

}
