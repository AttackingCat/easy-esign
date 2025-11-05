package io.github.easy.esign.struct.doc.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模板控件预填内容
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComponentFillingValue {

    /**
     * 控件ID（设置合同模板时由e签宝系统自动生成）
     */
    String componentId;

    /**
     * 控件Key（设置合同模板时由用户自定义）
     */
    String componentKey;

    /**
     * 控件填充值
     */
    String componentValue;

}

