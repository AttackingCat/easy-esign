package io.github.easy.esign.struct.doc.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * https://open.esign.cn/doc/opendoc/file-and-template3/mv8a3i
 * <p>
 * 填写模板生成文件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilesCreateByDocTemplateReq {

    /**
     * 待填充的模板ID（通过【获取创建文件模板页面链接】接口获取）
     */
    String docTemplateId;

    /**
     * 填充后生成的文件名称（可自定义文件名称）
     *
     * 【注】文件名称不可含有以下9个特殊字符：/ \ : * " < > | ？以及所有emoji表情
     */
    String fileName;

    /**
     * 控件列表（控件ID和 控件Key 二选一传值）
     */
    List<Components> components;

    /**
     * 是否校验PDF模板中必填控件，默认：false
     */
    Boolean requiredCheck;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Components {

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
         *
         * 补充说明：
         *
         * （1）可根据控件类型进行填充，点击查看填充值示例；
         *
         * （2）填充动态表格控件时，若需新增一行数据时 insertRow 参数值必须传 true；
         *
         * （3）点击查看如何填充动态表格。
         */
        String componentValue;

    }


}
