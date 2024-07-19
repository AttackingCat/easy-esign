package io.github.easy.esign.struct.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.easy.esign.struct.constant.IDCardType;
import io.github.easy.esign.struct.constant.OrgIDCardType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrgInfo {
    //组织机构证件号
    String orgIDCardNum;
    //组织机构证件类型
    OrgIDCardType orgIDCardType;
    //法定代表人姓名
    String legalRepName;
    //法定代表人证件号
    String legalRepIDCardNum;
    //法定代表人证件类型
    IDCardType legalRepIDCardType;
    //企业对公打款银行账户
    String orgBankAccountNum;
}