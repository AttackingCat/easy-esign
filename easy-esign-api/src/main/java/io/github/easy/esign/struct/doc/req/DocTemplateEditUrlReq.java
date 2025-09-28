package io.github.easy.esign.struct.doc.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * https://open.esign.cn/doc/opendoc/file-and-template3/lgb2go
 * <p>
 * 获取编辑合同模板页面
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocTemplateEditUrlReq {

    String docTemplateId;

}
