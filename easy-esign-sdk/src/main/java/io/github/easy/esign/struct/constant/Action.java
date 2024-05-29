package io.github.easy.esign.struct.constant;

public enum Action {
    //文件模板类通知事件
    EDIT_DOCTEMPLATE,//文件模板创建或编辑完成通知

    FILL_DOCTEMPLATE,//文件模板填写完成通知

    FILL_DOCTEMPLATE_FAIL,//文件模板填写失败通知
    OPERATOR_READ,//签署方-已读通知

    //签署类通知事件
    SIGN_MISSON_COMPLETE,//签署方-签署结果通知

    SIGN_FLOW_COMPLETE,//流程结束通知

    SIGN_FLOW_INITIATED,//签署发起成功通知

    OPERATOR_CORRECT_IDENTITY,//签署人更正个人信息回调通知

    TRANSMISS_SIGN,//经办人转交签署任务通知

    SIGN_SEAL_EXAMINE_REJECTED,//用印审批驳回通知

    SIGN_FILE_RESCISSION_INITIATE,//合同发起解约通知

    SIGN_FILE_RESCINDED,//合同解约成功通知

    COPIER_READ,//抄送方-已读通知

    //审批类通知事件
    INITIATE_APPROVAL,//发起审批流程通知

    APPROVAL_FINISH,//完结审批流程通知

    APPROVAL_TASK_START,//审批任务开启通知

    APPROVAL_TASK_COMPLETE,//审批任务完成通知

    APPROVAL_TASK_TRANS,//审批任务转交通知
}