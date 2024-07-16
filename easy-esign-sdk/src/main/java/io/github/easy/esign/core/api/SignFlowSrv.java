package io.github.easy.esign.core.api;

import io.github.easy.esign.core.BaseExecute;
import io.github.easy.esign.core.api.abs.SrvTemp;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.doc.resp.FilesCreateByDocTemplateReq;
import io.github.easy.esign.struct.sign.resp.*;
import io.github.easy.esign.struct.sign.req.SignFlowCreateByFileReq;
import io.github.easy.esign.struct.sign.req.SignFlowSignUrlReq;
import io.github.easy.esign.struct.sign.req.SignflowsSignfieldsPlatformSignReq;
import io.github.easy.esign.utils.UrlUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import io.github.easy.esign.struct.doc.resp.FilesCreateByDocTemplateResp;
import lombok.Synchronized;

/**
 * 签署
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignFlowSrv extends SrvTemp {

    private static BaseExecute execute;
    private static SignFlowSrv instance;

    @Synchronized
    public static SignFlowSrv getInstance() {
        if (instance == null) {
            instance = new SignFlowSrv();
            execute = getExecute(SignFlowSrv.class);
        }
        return instance;
    }

    /**
     * 填写模板生成文件
     */
    public ESignResp<FilesCreateByDocTemplateResp> filesCreateByDocTemplate(FilesCreateByDocTemplateReq request) {
        String path = "/v3/files/create-by-doc-template";
        return execute.post(path, request, FilesCreateByDocTemplateResp.class);
    }

    /**
     * 基于文件发起签署
     *
     */
    public ESignResp<SignFlowCreateByFileResp> createByFile(SignFlowCreateByFileReq request) {
        String path = "/v3/sign-flow/create-by-file";
        return execute.post(path, request, SignFlowCreateByFileResp.class);
    }

    /**
     * 添加平台方自动盖章签署区
     *
     */
    public ESignResp<SignflowsSignfieldsPlatformSignResp> signfieldsPlatformSign(SignflowsSignfieldsPlatformSignReq request) {
        String path = "/v1/signflows/" + request.getSignFlowId() + "/signfields/platformSign";
        return execute.post(path, request, SignflowsSignfieldsPlatformSignResp.class);
    }

    /**
     * 获取签署页面链接
     *
     */
    public ESignResp<SignFlowSignUrlResp> signUrl(SignFlowSignUrlReq request) {
        String path = "/v3/sign-flow/" + request.getSignFlowId() + "/sign-url";
        return execute.post(path, request, SignFlowSignUrlResp.class);
    }

    /**
     * 查询签署流程详情
     *
     */
    public ESignResp<SignFlowDetailResp> detail(String signFlowId) {
        String path = "/v3/sign-flow/" + signFlowId + "/detail";
        return execute.get(path, SignFlowDetailResp.class);
    }

    /**
     * 开启签署流程
     *
     */
    public ESignResp<String> start(String signFlowId) {
        String path = "/v3/sign-flow/" + signFlowId + "/start";
        return execute.post(path, "", String.class);
    }

    /**
     * 获取合同链接
     *
     */
    public ESignResp<SignFlowFileDownloadUrlResp> fileDownloadUrl(String signFlowId) {
        String path = UrlUtil.fmtPathUrl("/v3/sign-flow/{}/file-download-url", signFlowId);
        return execute.get(path, SignFlowFileDownloadUrlResp.class);
    }
}
