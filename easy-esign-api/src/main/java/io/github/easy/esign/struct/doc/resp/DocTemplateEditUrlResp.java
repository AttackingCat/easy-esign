package io.github.easy.esign.struct.doc.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * https://open.esign.cn/doc/opendoc/file-and-template3/lgb2go
 * <p>
 * 获取编辑合同模板页面
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocTemplateEditUrlResp {

    String docTemplateEditUrl;

}
