package io.github.easy.esign.struct.commen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.easy.esign.struct.constant.IDCardType;

/**
 * 个人身份附加信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PsnInfo {

    /**
     * 姓名
     */
    String psnName;

    /**
     * 证件号码
     */
    String psnIDCardNum;

    /**
     * 证件类型，可选值如下：
     */
    IDCardType psnIDCardType;

    /**
     * 个人手机号（运营商实名登记手机号或银行卡预留手机号，仅用于认证）
     */
    String psnMobile;

    /**
     * 个人银行卡号
     */
    String bankCardNum;
}