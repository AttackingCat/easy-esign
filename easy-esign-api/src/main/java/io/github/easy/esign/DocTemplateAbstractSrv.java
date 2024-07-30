package io.github.easy.esign;

import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.doc.req.DocTemplateEditUrlReq;
import io.github.easy.esign.struct.doc.resp.DocTemplateEditUrlResp;
import io.github.easy.esign.struct.doc.resp.DocTemplateResp;
import io.github.easy.esign.struct.doc.resp.DocTemplatesResp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Synchronized;

/**
 * 文件&模板
 */
public class DocTemplateAbstractSrv extends AbstractSrv {

    private static DocTemplateAbstractSrv instance;

    @Synchronized
    public static DocTemplateAbstractSrv getInstance() {
        if (instance == null) {
            instance = new DocTemplateAbstractSrv();
        }
        return instance;
    }

    /**
     * 查询合同模板中控件详情
     *
     * @param docTemplateId 合同模板文档ID
     * @return 合同模板
     */
    public ESignResp<DocTemplateResp> get(String docTemplateId) {
        String path = "/v3/doc-templates/" + docTemplateId;
        return execute().get(path, DocTemplateResp.class);
    }

    /**
     * 查询合同模板列表
     */
    public ESignResp<DocTemplatesResp> docTemplates(int pageNum, int pageSize) {
        // 注意参数需要按照排序
        String path = "/v3/doc-templates?pageNum=" + pageNum + "&pageSize=" + pageSize;
        return execute().get(path, DocTemplatesResp.class);
    }

    /**
     * 获取编辑合同模板页面
     */
    public ESignResp<DocTemplateEditUrlResp> editUrl(DocTemplateEditUrlReq request) {
        String path = "/v3/doc-templates/" + request.getDocTemplateId() + "/doc-template-edit-url";
        return execute().post(path, request, DocTemplateEditUrlResp.class);
    }

}
