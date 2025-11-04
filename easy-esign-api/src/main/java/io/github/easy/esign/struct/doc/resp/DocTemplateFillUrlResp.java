package io.github.easy.esign.struct.doc.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * https://open.esign.cn/doc/opendoc/pdf-sign3/ub4ncy
 * <p>
 * 获取填写合同模板页面响应
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocTemplateFillUrlResp {

    /**
     * 填写任务ID
     */
    String fillTaskId;

    /**
     * 填写文件模板页面链接（30天有效）
     */
    String docTemplateFillUrl;

    /**
     * 填写文件模板页面长链接（30天有效）
     * 支持自定义域名，微信小程序H5内嵌场景需要使用长链接
     */
    String docTemplateFillLongUrl;

}

