package io.github.easy.esign.struct.sign.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * https://open.esign.cn/doc/opendoc/paas_api/wvsbd0
 * <p>
 * 签署流程撤销
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowRevokeReq {

    /**
     * 签署流程ID
     */
    String signFlowId;

    /**
     * 发起人账户id，即发起本次签署的操作人个人账号id；如不传，默认由对接平台发起
     */
    String operatorId;

    /**
     * 撤销原因,默认"撤销"（最长400字节）
     * 补充说明：
     * 撤销原因不可含有以下9个特殊字符：/ \ : * " < > | ？以及所有emoji表情
     */
    String revokeReason;
}
