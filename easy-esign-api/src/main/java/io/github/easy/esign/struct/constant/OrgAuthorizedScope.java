package io.github.easy.esign.struct.constant;

public enum OrgAuthorizedScope {
    //授权当前应用AppId获取用户的账号基本信息：
    get_org_identity_info,//授权允许获取企业/组织的基本信息（需要授权获取的只有企业的法定代表人证件号，其他信息不授权可直接获取）
    get_psn_identity_info,//授权允许获取经办人个人用户的账号信息（姓名、手机号/邮箱、证件号等）

    //授权当前应用AppId代用户发起合同签署：
    org_initiate_sign,//授权允许代表企业/组织用户发起合同签署以及查询合同签署详情
    psn_initiate_sign,//授权允许代表经办人个人用户发起合同签署以及查询合同签署详情

    //授权当前应用AppId获取用户资源管理权限：
    manage_org_resource,//授权允许获取企业/组织用户的印章、组织成员等资源的管理权限（不包含用印权限）
    manage_psn_resource,//授权允许获取经办人个人用户的印章等资源的管理权限

    //授权当前应用AppId存储用户的合同文件
    // （用于平台专属云项目代客户发起合同签署场景）
    psn_sign_file_storage,//授权个人合同文件存储到平台应用的本地服务器
    org_sign_file_storage,//授权企业/组织合同文件存储到平台应用的本地服务器

    //授权当前应用AppId获取用户的用印审批信息：
    org_approval_info,//授权允许获取企业/组织用户的用印审批信息

    //授权当前应用AppId获取用户订单使用权限：
    use_org_order,//授权允许获取企业/组织用户套餐订单的使用权限
}
