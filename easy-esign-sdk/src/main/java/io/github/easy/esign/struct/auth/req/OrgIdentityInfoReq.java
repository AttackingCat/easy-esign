package io.github.easy.esign.struct.auth.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.easy.esign.struct.constant.OrgIDCardType;
import io.github.easy.esign.utils.StrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * https://open.esign.cn/doc/opendoc/auth3/vssvtu
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

    /**
     * 获取参数
     *
     * @return
     */
    public String toParam() {
        List<String> params = new ArrayList<>();
        if (StrUtil.isNotBlank(orgId)) {
            params.add("orgId=" + orgId);
        }
        if (StrUtil.isNotBlank(orgName)) {
            params.add("orgName=" + orgName);
        }
        if (StrUtil.isNotBlank(orgIDCardNum)) {
            params.add("orgIDCardNum=" + orgIDCardNum);
        }
        if (orgIDCardType != null) {
            params.add("orgIDCardType=" + orgIDCardType);
        }
        if (params.isEmpty()) {
            return null;
        }
        return String.join("&", params);
    }

}
