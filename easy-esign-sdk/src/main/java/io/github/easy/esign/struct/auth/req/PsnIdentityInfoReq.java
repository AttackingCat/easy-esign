package io.github.easy.esign.struct.auth.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.easy.esign.struct.constant.IDCardType;
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
public class PsnIdentityInfoReq {

    /**
     * 个人账号ID
     */
    String psnId;

    /**
     * 个人账号ID
     */
    String psnAccount;

    /**
     * 个人账号ID
     */
    String psnIDCardNum;

    /**
     * 个人账号ID
     */
    IDCardType psnIDCardType;

    /**
     * 获取参数
     *
     * @return
     */
    public String toParam() {
        List<String> params = new ArrayList<>();
        if (StrUtil.isNotBlank(psnAccount)) {
            params.add("psnAccount=" + psnAccount);
        }
        if (StrUtil.isNotBlank(psnId)) {
            params.add("psnId=" + psnId);
        }
        if (StrUtil.isNotBlank(psnIDCardNum)) {
            params.add("psnIDCardNum=" + psnIDCardNum);
        }
        if (psnIDCardType != null) {
            params.add("psnIDCardType=" + psnIDCardType);
        }
        if (params.isEmpty()) {
            return null;
        }
        return String.join("&", params);
    }

}
