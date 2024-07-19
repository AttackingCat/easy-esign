package io.github.easy.esign.struct.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.easy.esign.struct.constant.PsnAuthMode;
import io.github.easy.esign.struct.constant.PsnAvailableAuthMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.easy.esign.struct.constant.OrgAvailableAuthMode;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthConfig {
    private List<PsnAvailableAuthMode> psnAvailableAuthModes; // 个人实名认证方式可选项
    private List<OrgAvailableAuthMode> orgAvailableAuthModes; // 机构实名认证方式可选项
    private List<PsnAuthMode> willingnessAuthModes; // 签署意愿认证方式可选项
}