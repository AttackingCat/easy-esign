package io.github.easy.esign.struct.doc.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * https://open.esign.cn/doc/opendoc/file-and-template3/mghz1g
 * <p>
 * 查询合同模板列表
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocTemplatesResp {

    /**
     * 查询结果中模板的总数量
     */
    Long total;

    /**
     * 合同模板列表
     */
    List<DocTemplate> docTemplates;

    @Data
    public static class DocTemplate {

        String docTemplateId;
        String docTemplateName;
        Long createTime;
        Long updateTime;
    }

}
