package io.github.easy.esign.struct.sign.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * https://open.esign.cn/doc/opendoc/pdf-sign3/pvfkwd
 * <p>
 * 获取签署页面链接
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowSignUrlReq {

    /**
     * 签署流程ID
     */
    String signFlowId;

    /**
     * 是否需要登录打开链接（默认值 false）
     * <p>
     * true - 需登录打开链接，false - 免登录
     */
    Boolean needLogin;

    /**
     * 链接类型（默认值 2）
     * <p>
     * 1 - 预览链接（仅限查看，不能签署）， 2 - 签署链接
     */
    Integer urlType;

    /**
     * 指定客户端类型，当urlType为2（签署链接）时生效
     * <p>
     * H5 - 移动端适配
     * <p>
     * PC - PC端适配
     * <p>
     * ALL - 自动适配移动端或PC端（默认值）
     * <p>
     * 【注】参数值均为大写的英文
     */
    String clientType;

    /**
     * 指定签署操作人
     */
    Operator operator;

    /**
     * 重定向配置项
     */
    RedirectConfig redirectConfig;

    /**
     * AppScheme，用于唤起App。
     * <p>
     * 示例值：esign://demo/signBack
     */
    String appScheme;


    /**
     * 指定签署操作人
     * <p>
     * 当获取签署链接场景，需传入当前流程流转到的签署操作人信息。（如不传该参数，后台默认自动带入appId对应主体信息）
     * psnAccount与psnId二选一传入（必须与发起签署时的账号保持一致）
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Operator {

        /**
         * 签署操作人账号标识（手机号/邮箱号）
         */
        String psnAccount;

        /**
         * 签署操作人账号ID（个人账号ID）
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
         * 操作完成后页面重定向跳转延迟时间，单位为秒，默认3秒
         */
        String redirectDelayTime;

    }


}
