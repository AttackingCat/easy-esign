package io.github.easy.esign.struct.doc.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * https://open.esign.cn/doc/opendoc/file-and-template3/aoq509
 * <p>
 * 查询合同模板中控件详情
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocTemplateResp {

    /**
     * 合同模板ID
     */
    String docTemplateId;

    /**
     * 合同模板名称
     */
    String docTemplateName;

    /**
     * 合同模板创建时间（Unix时间戳格式，单位：毫秒）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;

    /**
     * 合同模板更新时间（Unix时间戳格式，单位：毫秒）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updateTime;

    /**
     * 合同模板的底稿PDF文件下载链接
     * <p>
     * 链接有效期为60分钟
     * 当模板类型为html模板时，默认会返回 null
     */
    String fileDownloadUrl;

    /**
     * 模板中的控件列表信息
     */
    List<Component> components;

    /**
     * 模板中的控件列表信息
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Component {

        /**
         * 控件ID（设置合同文件模板时由e签宝系统生成）
         */
        String componentId;

        /**
         * 控件Key（设置合同文件模板时由用户自定义）
         */
        String componentKey;

        /**
         * 控件名称
         */
        String componentName;

        /**
         * 控件是否必填
         *
         * true - 必填，false - 非必填
         */
        Boolean required;

        /**
         * 控件类型
         *
         * 1 - 单行文本，2 - 数字，3 - 日期，6 - 签章区域，8 - 多行文本，9 - 复选，10 - 单选，11 - 图片，14 -下拉框，15 - 勾选框，16 - 身份证，17 - 备注区域，18 - 动态表格，19 - 手机号
         */
        Integer componentType;

        /**
         * 控件位置信息
         */
        ComponentPosition componentPosition;

        /**
         * 控件特有属性
         */
        ComponentSpecialAttribute componentSpecialAttribute;

        /**
         * 控件尺寸
         */
        ComponentSize componentSize;

        /**
         * 签章区属性
         */
        NormalSignField normalSignField;

        /**
         * 备注区属性
         */
        RemarkSignField remarkSignField;

        /**
         * 自定义控件id
         *
         * （【获取制作文件模板页面链接】接口传入的自定义控件id值）
         */
        String originCustomComponentId;

        /**
         * 控件位置信息
         */
        @Data
        public static class ComponentPosition {

            /**
             * 控件位置X横坐标
             */
            Double componentPositionX;

            /**
             * 控件位置Y纵坐标
             */
            Double componentPositionY;

            /**
             * 控件所在页码
             */
            Integer componentPageNum;
        }

        /**
         * 控件特有属性
         */
        @Data
        public static class ComponentSpecialAttribute {

            /**
             * 日期格式（日期控件特有）
             */
            String dateFormat;

            /**
             * 图片类型（图片控件特有）
             *
             * IDCard_widthwise（身份证横向，等比缩放大小）
             *
             * IDCard_longitudinal（身份证纵向，等比缩放大小）
             *
             * other （其他，自由缩放大小）
             */
            String imageType;

            /**
             * 选项（下拉框控件、单选控件、多选控件特有）
             */
            List<Options> options;

            /**
             * 表格行列内容（动态表格控件特有），格式：
             *
             * [row{"column1":"value1","column2":"value2"}]
             *
             * 补充说明：
             *
             * row 表示动态表格对应的行，row的个数依据模板动态表格控件中所添加的所添加的行数。
             * column1 表示当前行中单元格的Key值
             * value1 表示当前行中单元格的Value值，单元格未设置固定值时为""空字符串，否则为所设置的固定值。
             * 详见 tableContent 解释说明。
             */
            List<Map<String, Object>> tableContent;

            /**
             * 数字格式（数字控件特有），格式如下：
             *
             * 0 - 整数，0.0 - 保留一位小数，0.00 - 保留两位小数
             */
            String numberFormat;

            /**
             * 可填充的长度上限，单位：中文字符
             *
             * 【注】1个中文字符=2个英文字符；
             *
             * 仅适用于制作PDF模板时，返回此参数；
             */
            String componentMaxLength;

            /**
             * 选项（下拉框控件、单选控件、多选控件特有）
             */
            @Data
            public static class Options {

                /**
                 * 选项内容
                 */
                String optionContent;

                /**
                 * 选项顺序
                 */
                Integer optionOrder;

                /**
                 * 选项是否默认选中
                 */
                Boolean selected;
            }
        }

        /**
         * 控件尺寸
         */
        @Data
        public static class ComponentSize {

            /**
             * 控件宽度（矩形的左右距离，单位为px）
             */
            Double componentWidth;

            /**
             * 控件高度（矩形的上下距离，单位为px）
             */
            Double componentHeight;

        }


        /**
         * 签章区属性
         */
        @Data
        public static class NormalSignField {

            /**
             * 是否显示签署日期
             *
             * 0 - 不显示，1 - 显示
             */
            Integer showSignDate;

            /**
             * 日期格式，支持以下日期格式：
             *
             * yyyy年MM月dd日
             *
             * yyyy-MM-dd
             *
             * yyyy/MM/dd
             *
             * yyyy-MM-dd HH:mm:ss
             */
            String dateFormat;

            /**
             * 签章样式
             *
             * 1 - 单页签章，2 - 骑缝签章
             */
            Integer signFieldStyle;

        }

        @Data
        public static class RemarkSignField {

            /**
             * 是否开启手写抄录AI校验
             *
             * 0 - 不开启，1 - 开启 AI 校验，2 - 强制 AI 校验
             */
            Integer aiCheck;

            /**
             * 备注文字输入方式
             *
             * 1 - 手写抄录方式，2 - 自由输入方式
             */
            Integer inputType;

            /**
             * 预设手写抄录信息
             */
            String remarkContent;

            /**
             * 备注文字的字号，单位pt，默认值12pt
             *
             * 注：签署侧需要的字号单位是px，模板侧通用的都是pt，因此要做一次转换；pt与px间的换算关系是：0.75px=1pt
             */
            String remarkFontSize;

        }


    }

}
