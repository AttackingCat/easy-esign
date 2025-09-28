package io.github.easy.esign.struct.doc.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * https://open.esign.cn/doc/opendoc/file-and-template3/mv8a3i
 * <p>
 * 填写模板生成文件
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilesCreateByDocTemplateResp {

    /**
     * 填充后生成的文件ID
     */
    String fileId;

    /**
     * 文件下载地址（有效期60分钟）
     * <p>
     * 填充PDF模板时，返回填充后的文件下载地址。
     * 填充HTML模板时，默认返回null，如需获取文件下载地址，建议调用【查询文件上传状态】接口，传入上方参数“填充后生成的文件ID”的返回值来获取。
     */
    String fileDownloadUrl;
}
