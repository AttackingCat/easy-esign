package io.github.easy.esign.struct.sign.notify;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import io.github.easy.esign.struct.constant.Action;

import java.util.Date;

/**
 * https://open.esign.cn/doc/opendoc/notify3/sblzg8#kDrh0
 * <p>
 * 签署回调通知接收说明
 */
@Data
public class SignFlowNotify {

    /**
     * 是否是某个指定的Action
     *
     * @param action
     * @return boolean
     */
    public boolean isAction(Action action) {
        return action != null && action.toString().equals(this.action);
    }

    /**
     * 是否签署完成
     *
     * @return boolean
     */
    public boolean isSigned() {
        return Integer.valueOf(2).equals(this.signResult);
    }

    /**
     * 是否拒签
     *
     * @return boolean
     */
    public boolean isReject() {
        return Integer.valueOf(4).equals(this.signResult);
    }


    /**
     * 标记该通知的业务类型
     */
    String action;              // 标记该通知的业务类型，该通知固定为：SIGN_FLOW_COMPLETE
    Date timestamp;             // 回调通知触发时间（如重试多次均返回第一次时间，毫秒级时间戳格式）
    String signFlowId;          // 签署流程ID
    String customBizNum;        // 自定义业务编号 该参数取值设置签署区的时候设置的customBizNum参数
    Integer signOrder;          // 当前签署顺序
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date readTime;              // 已读时间，格式：yyyy-MM-dd HH:mm:ss
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date operateTime;

    /**
     * 签署结果
     * <p>
     * 2 - 签署完成，3 - 失败，4 - 拒签
     */
    Integer signResult;
    /**
     * 拒签或失败时，附加的原因描述
     */
    String resultDescription;

    /**
     * 操作人信息
     */
    Operator operator;

    /**
     * 机构签署方
     */
    Organization organization;

    @Data
    public static class Operator {
        String psnId;
        PsnAccount psnAccount;

        @Data
        public static class PsnAccount {
            String accountMobile;
            String accountEmail;
        }
    }

    @Data
    public static class Organization {
        String orgId;
        String orgName;
    }

}
