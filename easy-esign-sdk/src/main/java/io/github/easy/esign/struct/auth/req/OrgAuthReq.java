package io.github.easy.esign.struct.auth.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.easy.esign.struct.constant.OrgAuthorizedScope;
import io.github.easy.esign.struct.commen.OrgInfo;
import io.github.easy.esign.struct.commen.TransactorInfo;
import io.github.easy.esign.struct.constant.ClientType;
import io.github.easy.esign.struct.constant.OrgAuthMode;
import io.github.easy.esign.struct.constant.OrgAvailableAuthMode;
import io.github.easy.esign.struct.constant.OrgEditableFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class OrgAuthReq {

    //机构授权配置项
    private OrgAuthConfig orgAuthConfig;
    //机构授权配置项
    private AuthorizeConfig authorizeConfig;
    //认证完成重定向配置项
    private RedirectConfig redirectConfig;
    //指定客户端类型，默认值 ALL（注意参数值全部为英文大写）
    private ClientType clientType;
    //当前经办人非企业管理员的情况下，是否需要为其获取企业全部印章的用印权限，默认false
    private String transactorUseSeal;
    //接收回调通知的Web地址（需符合 https /http 协议），通知开发者用户认证和授权的完成情况，点击详见通知说明。
    private String notifyUrl;
    //AppScheme，用于支付宝人脸认证重定向时唤起指定App。
    //示例值：esign://demo/realBack
    private String appScheme;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrgAuthConfig {
        //组织机构名称（机构账号标识）
        private String orgName;
        //机构账号ID
        private String orgId;
        //组织机构身份附加信息
        private OrgInfo orgInfo;
        //机构实名认证页面配置项
        private OrgAuthPageConfig orgAuthPageConfig;
        //经办人身份信息
        private TransactorInfo transactorInfo;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class OrgAuthPageConfig {
            //指定页面中默认选择的机构认证方式：
            private OrgAuthMode orgDefaultAuthMode;
            //设置页面中可选择的机构认证方式，若不传此参数，则可选择全部认证方式
            private List<OrgAvailableAuthMode> orgAvailableAuthModes;
            //设置页面中可编辑的信息，不传此参数，页面默认不允许编辑机构信息。
            private List<OrgEditableFields> orgEditableFields;
        }




    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorizeConfig {
        private List<OrgAuthorizedScope> authorizedScopes;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RedirectConfig {
        //认证完成后跳转页面（需符合 https /http 协议地址）
        private String redirectUrl;
        //重定向跳转延迟时间，单位为秒。
        private String redirectDelayTime;
    }
}
