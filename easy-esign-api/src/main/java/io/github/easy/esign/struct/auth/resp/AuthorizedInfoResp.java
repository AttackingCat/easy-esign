package io.github.easy.esign.struct.auth.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * https://open.esign.cn/doc/opendoc/auth3/nurtvw
 * <p>
 * 查询个人授权信息
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorizedInfoResp {

    /**
     * 用户授权信息
     */
    List<AuthorizedInfo> authorizedInfo;

    /**
     * 用户授权信息
     */
    @Data
    public static class AuthorizedInfo {

        /**
         * 用户授权范围
         * <p>
         * get_psn_identity_info - 授权允许获取个人用户的账号基本信息
         * <p>
         * psn_initiate_sign - 授权允许代表个人用户发起合同签署
         * <p>
         * manage_psn_resource - 授权允许获取个人用户的印章等资源的管理权限
         */
        String authorizedScope;

        /**
         * 授权生效时间（Unix时间戳格式，单位毫秒）
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        Date effectiveTime;

        /**
         * 授权到期时间（Unix时间戳格式，单位毫秒）
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        Date expireTime;


    }

}
