package io.github.easy.esign.struct.doc.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * https://open.esign.cn/doc/opendoc/pdf-sign3/ub4ncy
 * <p>
 * 获取填写合同模板页面
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocTemplateFillUrlReq {

    /**
     * 文件模板ID
     */
    String docTemplateId;

    /**
     * 自定义业务编号
     */
    String customBizNum;

    /**
     * 模板控件预填内容列表（控件ID和控件Key二选一传值）
     */
    List<ComponentFillingValue> componentFillingtValues;

    /**
     * 用户填写页面是否可以修改预填内容，默认为true
     * true - 可修改
     * false - 不可修改
     */
    Boolean editFillingValue;

    /**
     * 指定客户端类型
     * ALL - 自动适配移动端或PC端（默认值）
     * H5 - 移动端适配
     * PC - PC端适配
     */
    String clientType;

    /**
     * 回调通知地址
     */
    String notifyUrl;

    /**
     * 填写模板完成后跳转页面（需符合 https /http 协议地址）
     */
    String redirectUrl;

}

