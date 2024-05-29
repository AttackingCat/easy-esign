package io.github.easy.esign.api;

import io.github.easy.esign.core.ESignManager;
import io.github.easy.esign.struct.doc.req.DocTemplateEditUrlReq;
import io.github.easy.esign.struct.doc.resp.DocTemplateEditUrlResp;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.doc.resp.DocTemplateResp;
import io.github.easy.esign.struct.doc.resp.DocTemplatesResp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 文件&模板
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ESignDocTemplateSrv {

    private static volatile ESignDocTemplateSrv INSTANCE;

    public static synchronized ESignDocTemplateSrv getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ESignDocTemplateSrv();
        }
        return INSTANCE;
    }

    /**
     * 查询合同模板中控件详情
     *
     * @param docTemplateId 合同模板文档ID
     * @return 合同模板
     */
    public ESignResp<DocTemplateResp> get(String docTemplateId) {
        String path = "/v3/doc-templates/" + docTemplateId;
        return ESignManager.getContext().get(path, DocTemplateResp.class);
    }

    /**
     * 查询合同模板列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ESignResp<DocTemplatesResp> docTemplates(int pageNum, int pageSize) {
        // 注意参数需要按照排序
        String path = "/v3/doc-templates?pageNum=" + pageNum + "&pageSize=" + pageSize;
        return ESignManager.getContext().get(path, DocTemplatesResp.class);
    }

    /**
     * 获取编辑合同模板页面
     *
     * @param request
     * @return
     */
    public ESignResp<DocTemplateEditUrlResp> editUrl(DocTemplateEditUrlReq request) {
        String path = "/v3/doc-templates/" + request.getDocTemplateId() + "/doc-template-edit-url";
        return ESignManager.getContext().post(path, request, DocTemplateEditUrlResp.class);
    }

}
