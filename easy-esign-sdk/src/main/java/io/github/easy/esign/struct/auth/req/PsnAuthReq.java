package io.github.easy.esign.struct.auth.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.easy.esign.struct.commen.PsnInfo;

import java.util.List;

/**
 * https://open.esign.cn/doc/opendoc/auth3/rx8igf
 * <p>
 * 获取个人认证&授权页面链接
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PsnAuthReq {

    /**
     * 个人实名认证配置项
     */
    PsnAuthConfig psnAuthConfig;

    /**
     * 个人授权配置项
     */
    AuthorizeConfig authorizeConfig;

    /**
     * 认证完成重定向配置项
     */
    RedirectConfig redirectConfig;

    /**
     * 接收回调通知的Web地址，通知开发者用户认证和授权的完成以及变更情况，
     * <p>
     * 点此了解更多认证授权回调通知。
     */
    String notifyUrl;

    /**
     * 指定客户端类型，默认值 ALL（注意参数值全部为英文大写）
     * <p>
     * ALL - 自动适配移动端或PC端
     * <p>
     * H5 - 移动端适配
     * <p>
     * PC - PC端适配
     */
    String clientType;

    /**
     * AppScheme，用于支付宝人脸认证重定向时唤起指定App。
     * <p>
     * 示例值：esign://demo/realBack
     */
    String appScheme;


    /**
     * 个人实名认证配置项
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PsnAuthConfig {

        /**
         * 个人用户账号标识（手机号或邮箱）
         * <p>
         * 【注】若未知用户的psnId/用户未实名，可以传此字段
         */
        String psnAccount;

        /**
         * 个人账号ID
         * <p>
         * 【注】若已知用户的psnId/用户已实名，可以传此字段
         */
        String psnId;

        /**
         * 个人身份附加信息
         */
        PsnInfo psnInfo;

    }

    /**
     * 个人授权配置项
     * <p>
     * 授权过期需重新授权；
     * 不传此参数默认页面仅实名认证，不需要用户授权。
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorizeConfig {

        /**
         * 设置页面中权限范围，参数值如下：
         * <p>
         * 授权当前应用AppId获取用户的账号基本信息：
         * <p>
         * get_psn_identity_info - 授权允许获取个人用户的账号信息（姓名、手机号/邮箱、证件号等）
         * <p>
         * <p>
         * 授权当前应用AppId代用户发起合同签署：
         * <p>
         * psn_initiate_sign - 授权允许代表个人用户发起合同签署以及查询合同签署详情
         * <p>
         * <p>
         * 授权当前应用AppId获取用户资源管理权限：
         * <p>
         * manage_psn_resource - 授权允许获取个人用户的印章等资源的管理权限
         */
        List<String> authorizedScopes;

    }

    /**
     * 认证完成重定向配置项
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RedirectConfig {

        /**
         * 认证完成后跳转页面（需符合 https /http 协议地址）
         */
        String redirectUrl;

        /**
         * 重定向跳转延迟时间，单位为秒。
         * <p>
         * 授权模式下（authorizedScopes有具体的参数值）：默认延迟时间为 3秒。
         * <p>
         * 传 0 - 不展示授权结果页，认证完成直接跳转重定向地址
         * 传 其他数字 - 展示授权结果页，倒计时 x秒后，自动跳转重定向地址
         * 实名模式下（authorizedScopes不传或者没有具体的参数值）：默认延迟时间为 5秒。
         * <p>
         * 传 0 - 不展示实名结果页，认证完成直接跳转重定向地址
         * 传 其他数字 - 展示实名结果页，倒计时5秒后，自动跳转重定向地址（只有5秒，没有其他秒数的控制）
         * 【注】当redirectUrl不传的情况下，该字段无需传入，认证完成结果页不跳转。
         */
        String redirectDelayTime;

    }


}
