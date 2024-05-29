package io.github.easy.esign.api;

import io.github.easy.esign.core.ESignManager;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.doc.resp.FilesCreateByDocTemplateReq;
import io.github.easy.esign.struct.sign.resp.*;
import io.github.easy.esign.struct.sign.req.SignFlowCreateByFileReq;
import io.github.easy.esign.struct.sign.req.SignFlowSignUrlReq;
import io.github.easy.esign.struct.sign.req.SignflowsSignfieldsPlatformSignReq;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import io.github.easy.esign.struct.doc.resp.FilesCreateByDocTemplateResp;

/**
 * 签署
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ESignSignFlowSrv {
    private static volatile ESignSignFlowSrv INSTANCE;

    public static synchronized ESignSignFlowSrv getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ESignSignFlowSrv();
        }
        return INSTANCE;
    }

    /**
     * 填写模板生成文件
     *
     * @param request
     * @return
     */
    public ESignResp<FilesCreateByDocTemplateResp> filesCreateByDocTemplate(FilesCreateByDocTemplateReq request) {
        String path = "/v3/files/create-by-doc-template";
        return ESignManager.getContext().post(path, request, FilesCreateByDocTemplateResp.class);
    }

    /**
     * 基于文件发起签署
     *
     * @param request
     * @return
     */
    public ESignResp<SignFlowCreateByFileResp> createByFile(SignFlowCreateByFileReq request) {
        String path = "/v3/sign-flow/create-by-file";
        return ESignManager.getContext().post(path, request, SignFlowCreateByFileResp.class);
    }

    /**
     * 添加平台方自动盖章签署区
     *
     * @param request
     * @return
     */
    public ESignResp<SignflowsSignfieldsPlatformSignResp> signfieldsPlatformSign(SignflowsSignfieldsPlatformSignReq request) {
        String path = "/v1/signflows/" + request.getSignFlowId() + "/signfields/platformSign";
        return ESignManager.getContext().post(path, request, SignflowsSignfieldsPlatformSignResp.class);
    }

    /**
     * 获取签署页面链接
     *
     * @param request
     * @return
     */
    public ESignResp<SignFlowSignUrlResp> signUrl(SignFlowSignUrlReq request) {
        String path = "/v3/sign-flow/" + request.getSignFlowId() + "/sign-url";
        return ESignManager.getContext().post(path, request, SignFlowSignUrlResp.class);
    }

    /**
     * 查询签署流程详情
     *
     * @param signFlowId
     * @return
     */
    public ESignResp<SignFlowDetailResp> detail(String signFlowId) {
        String path = "/v3/sign-flow/" + signFlowId + "/detail";
        return ESignManager.getContext().get(path, SignFlowDetailResp.class);
    }

    /**
     * 开启签署流程
     *
     * @param signFlowId
     * @return
     */
    public ESignResp<String> start(String signFlowId) {
        String path = "/v3/sign-flow/" + signFlowId + "/start";
        return ESignManager.getContext().post(path, "", String.class);
    }

    /**
     * 获取合同链接
     *
     * @param signFlowId
     * @return
     */
    public ESignResp<SignFlowFileDownloadUrlResp> fileDownloadUrl(String signFlowId) {
        String path = String.format("/v3/sign-flow/%s/file-download-url", signFlowId);
        return ESignManager.getContext().get(path, SignFlowFileDownloadUrlResp.class);
    }
}
