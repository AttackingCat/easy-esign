package io.github.easy.esign.struct.constant;

public enum OrgEditableFields {
    orgNum,//机构证件号（如果账号已实名，传了该字段，页面也是不可编辑更改的，因为证件号是唯一标识）
    legalRepName,//法定代表人姓名
    orgBankAccountNum,//企业对公打款银行账户
}