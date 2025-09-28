package io.github.easy.esign.struct.sign.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * https://open.esign.cn/doc/opendoc/pdf-sign3/su5g42
 * <p>
 * 基于文件发起签署
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignFlowCreateByFileResp {

    /**
     * 签署流程ID（建议开发者保存此流程ID）
     */
    String signFlowId;

}
