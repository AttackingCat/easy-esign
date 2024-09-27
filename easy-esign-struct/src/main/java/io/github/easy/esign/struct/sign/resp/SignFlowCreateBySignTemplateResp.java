package io.github.easy.esign.struct.sign.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <a href="https://open.esign.cn/doc/opendoc/file-and-template3/megwsgkmpbg1tec1">通过流程模板创建合同拟定和签署流程</a>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignFlowCreateBySignTemplateResp {


    /**
     * 流程模板ID
     */
    String signFlowId;


}