package io.github.easy.esign.struct.commen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactorInfo {
    //经办人账号ID
    private String orgId;
    //经办人账号标识（手机号或邮箱）
    private String psnAccount;
    //经办人身份信息
    private PsnInfo psnInfo;
}