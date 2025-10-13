package io.github.easy.esign;

import io.github.easy.esign.core.AbstractSrv;
import io.github.easy.esign.error.ESignException;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.doc.resp.FilesCreateByDocTemplateReq;
import io.github.easy.esign.struct.doc.resp.FilesCreateByDocTemplateResp;
import io.github.easy.esign.struct.sign.req.*;
import io.github.easy.esign.struct.sign.resp.*;
import io.github.easy.esign.utils.StrUtil;

/**
 * 签署
 */
public class SignFlowSrv extends AbstractSrv {

    /**
     * 填写模板生成文件
     */
    public ESignResp<FilesCreateByDocTemplateResp> filesCreateByDocTemplate(FilesCreateByDocTemplateReq request) {
        String path = "/v3/files/create-by-doc-template";
        return execute().post(path, request, FilesCreateByDocTemplateResp.class);
    }

    /**
     * 基于文件发起签署
     */
    public ESignResp<SignFlowCreateByFileResp> createByFile(SignFlowCreateByFileReq request) {
        String path = "/v3/sign-flow/create-by-file";
        return execute().post(path, request, SignFlowCreateByFileResp.class);
    }

    /**
     * 通过流程模板创建合同拟定和签署流程
     */
    public ESignResp<SignFlowCreateBySignTemplateResp> createBySignTemplate(SignFlowCreateBySignTemplateReq request) {
        String path = "/v3/sign-flow/create-by-sign-template";
        return execute().post(path, request, SignFlowCreateBySignTemplateResp.class);
    }

    /**
     * 添加平台方自动盖章签署区
     */
    public ESignResp<SignflowsSignfieldsPlatformSignResp> signFieldsPlatformSign(SignflowsSignfieldsPlatformSignReq request) {
        String path = "/v1/signflows/" + request.getSignFlowId() + "/signfields/platformSign";
        return execute().post(path, request, SignflowsSignfieldsPlatformSignResp.class);
    }

    /**
     * 获取签署页面链接
     */
    public ESignResp<SignFlowSignUrlResp> signUrl(SignFlowSignUrlReq request) {
        String path = "/v3/sign-flow/" + request.getSignFlowId() + "/sign-url";
        return execute().post(path, request, SignFlowSignUrlResp.class);
    }

    /**
     * 查询签署流程详情
     */
    public ESignResp<SignFlowDetailResp> detail(String signFlowId) {
        String path = "/v3/sign-flow/" + signFlowId + "/detail";
        return execute().get(path, SignFlowDetailResp.class);
    }

    /**
     * 开启签署流程
     */
    public ESignResp<String> start(String signFlowId) {
        String path = "/v3/sign-flow/" + signFlowId + "/start";
        return execute().post(path, "", String.class);
    }

    /**
     * 获取合同链接
     */
    public ESignResp<SignFlowFileDownloadUrlResp> fileDownloadUrl(String signFlowId) {
        String path = "/v3/sign-flow/" + signFlowId + "/file-download-url";
        return execute().get(path, SignFlowFileDownloadUrlResp.class);
    }

    /**
     * 撤销签署流程
     */
    public ESignResp<String> revoke(SignFlowRevokeReq revokeReq) {
        if (StrUtil.isBlank(revokeReq.getSignFlowId())) {
            throw new ESignException("签约流程ID不能为空");
        }
        if (StrUtil.isNotBlank(revokeReq.getRevokeReason()) &&
                revokeReq.getRevokeReason().length() >= 50) {
            throw new ESignException("撤销原因长度过长");
        }
        String path = "/v3/sign-flow/" + revokeReq.getSignFlowId() + "/revoke";
        revokeReq.setSignFlowId(null);
        return execute().post(path, "", String.class);
    }
}
