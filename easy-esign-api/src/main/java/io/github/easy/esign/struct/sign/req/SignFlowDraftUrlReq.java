package io.github.easy.esign.struct.sign.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * https://open.esign.cn/doc/opendoc/file-and-template3/gu4n9g6fcaenhmnw
 * <p>
 * 获取填写页面链接 请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowDraftUrlReq {

    /**
     * 签署流程ID
     */
    String signFlowId;

    /**
     * 填写操作人（个人填写方本人为操作人，机构填写方经办人为操作人）
     * <p>
     * psnAccount与psnId二选一传入（必须与发起签署时的账号保持一致）
     */
    Operator operator;

    /**
     * 是否需要登录打开填写链接（默认值 true）
     * <p>
     * true - 需登录打开链接
     * <p>
     * false - 免登录【注】：免登录获取的填写链接默认30分钟有效，过期后可以重新调用接口获取新的链接。
     */
    Boolean needLogin;

    /**
     * 填写链接类型（默认值 2）
     * <p>
     * 1 - 预览链接
     * <p>
     * 2 - 填写链接
     * <p>
     * 【注】：预览链接可以查看用户填写中或填写完成的文件详情，方便业务场景中的审批等环节（操作人-operator必须是流程中的参与方）。
     */
    Integer urlType;

    /**
     * 指定客户端类型，默认值 ALL
     * <p>
     * ALL - 自动适配移动端或PC端
     * <p>
     * H5 - 移动端适配
     * <p>
     * PC - PC端适配
     * <p>
     * 【注】：微信小程序使用场景需要指定：H5
     */
    String clientType;

    /**
     * 重定向配置项
     */
    RedirectConfig redirectConfig;

    /**
     * 填写操作人
     * <p>
     * 个人填写方本人为操作人，机构填写方经办人为操作人
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Operator {

        /**
         * 填写人账号标识（手机号/邮箱号）
         */
        String psnAccount;

        /**
         * 填写人账号ID（个人账号ID）
         */
        String psnId;

    }

    /**
     * 重定向配置项
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RedirectConfig {

        /**
         * 重定向地址（需符合 https /http 协议地址）
         */
        String redirectUrl;

        /**
         * 操作完成后页面重定向跳转延迟时间，单位为秒，默认3秒。
         * <p>
         * 0-不展示填写完成结果页，填写完成直接跳转重定向地址。
         * <p>
         * X-展示填写完成结果页，倒计时X秒后，自动跳转重定向地址
         * <p>
         * 补充说明：当redirectUrl不传的情况下，该字段无需传入，默认填写完成结果页不跳转。没有传入redirectUrl但传入redirectDelayTime接口会报错。
         */
        Integer redirectDelayTime;

    }

}
