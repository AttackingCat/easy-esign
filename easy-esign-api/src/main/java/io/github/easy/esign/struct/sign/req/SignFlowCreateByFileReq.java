package io.github.easy.esign.struct.sign.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.easy.esign.struct.common.*;

import java.util.List;

/**
 * <a href="https://open.esign.cn/doc/opendoc/pdf-sign3/su5g42">基于文件发起签署</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowCreateByFileReq {

    /**
     * 设置待签署文件信息
     */
    List<Docs> docs;

    /**
     * 设置附属材料信息（指无需签名的文件，仅用于查看）
     */
    List<Attachments> attachments;

    /**
     * 签署流程配置项
     */
    SignFlowConfig signFlowConfig;

    /**
     * 签署方信息（指参与签署的个人或者机构）
     */
    List<Signers> signers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public static class Docs {

        /**
         * 待签署文件ID
         */
        String fileId;

        /**
         * 文件名称
         */
        String fileName;

        /**
         * 是否需要密码，默认false
         */
        Boolean neededPwd;

        /**
         * 文件编辑密码
         */
        String fileEditPwd;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Attachments {

        /**
         * 待签署文件ID
         */
        String fileId;

        /**
         * 文件名称
         */
        String fileName;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignFlowConfig {

        /**
         * 签署流程主题（将展示在签署通知和签署页的任务信息中）
         */
        String signFlowTitle;

        /**
         * 签署截止时间， unix时间戳（毫秒）格式，
         * <p>
         * 补充说明：
         * <p>
         * 默认在签署流程创建后的90天时截止（指定值最大不能超过90天，只能指定90天内的时间戳）。签署中如需延期请调用【延期签署截止时间】接口。
         */
        Long signFlowExpireTime;

        /**
         * 自动开启签署流程，默认值 true
         * <p>
         * true - 自动开启（发起签署流程，将直接进入“签署中”状态）
         * <p>
         * false - 非自动开启（发起“草稿”状态的签署流程，需调用【开启签署流程】接口后流程进入“签署中”状态）
         * <p>
         * 补充说明：
         * <p>
         * 自动开启的流程不允许再追加待签署文件，点击这里了解更多流程状态说明。
         */
        Boolean autoStart;

        /**
         * 所有签署方签署完成后流程自动完结，默认值 false
         * <p>
         * true - 自动完结
         * <p>
         * false - 非自动完结，需调用【完结签署流程】接口完结
         * <p>
         * 补充说明：
         * <p>
         * 【注】设置了自动完结的流程中不允许再追加签署区、抄送方，点击这里了解更多流程状态说明。
         */
        Boolean autoFinish;

        /**
         * 身份校验配置项（当开发者指定的签署人信息与该签署人在e签宝已有的身份信息不一致时如何处理），默认：true
         * <p>
         * true - 接口报错（提示：传入的指定签署人信息与实名信息不一致相关报错）
         * <p>
         * false - 不报错，正常发起（签署人可以在签署链接中修改账号信息，开发者再通过回调通知接收相关改动信息，详见【签署人更正个人信息回调通知】）。
         */
        Boolean identityVerify;

        /**
         * 接收相关回调通知的Web地址，详见【签署回调通知接收说明】。
         */
        String notifyUrl;

        /**
         *
         */
        RedirectConfig redirectConfig;


        /**
         * 重定向配置项
         */
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class RedirectConfig {

            /**
             * 签署完成后跳转页面（需符合 https /http 协议地址）
             */
            String redirectUrl;

            /**
             * 操作完成重定向跳转延迟时间，单位秒（可选值0、3，默认值为 3）
             * <p>
             * 传0时，签署完成直接跳转重定向地址；
             * <p>
             * 传3时，展示签署完成结果页，倒计时3秒后，自动跳转重定向地址。
             * <p>
             * 【注】当redirectUrl不传的情况下，该字段无需传入，签署完成不跳转
             */
            Integer redirectDelayTime;


        }
    }


    /**
     * 签署方信息（指参与签署的个人或者机构）
     * <p>
     * 单个签署方数组中，机构签署方、个人签署方二选一传入
     * （orgSignerInfo与psnSignerInfo二选一即可）；
     * <p>
     * 多方签署场景，可传多个签署方数组；
     * 单个签署流程中，签署方最多不能超过300个。
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Signers {

        /**
         * 签署人配置项
         */
        SignConfig signConfig;

        /**
         * 签署方维度认证配置项
         */
        AuthConfig authConfig;

        /**
         * 设置签署方的通知方式
         */
        NoticeConfig noticeConfig;
        /**
         * 签署方类型，0 - 个人，1 - 机构，2 - 法定代表人
         * <p>
         * 若指定签署方为个人，则psnSignerInfo为必传项；
         * 若指定签署方为机构或法定代表人手动签署（autoSign参数为false）时，则orgSignerInfo为必传项；
         */
        Integer signerType;

        /**
         * 个人签署方信息
         */
        PsnSignerInfo psnSignerInfo;

        /**
         * 个人签署方信息
         */
        OrgSignerInfo orgSignerInfo;


        /**
         * 签署区信息（设置签署方 盖章/签名/文字输入的区域）
         */
        List<SignFields> signFields;

        /**
         * 签署区信息（设置签署方 盖章/签名/文字输入的区域）
         */
        List<Copiers> copiers;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Copiers {
        //TODO
        String signTipsTitle;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignConfig {
        //设置签署方的签署顺序
        Integer signOrder;
        //设置签署页面强制阅读倒计时时间，默认值为 0（单位：秒，最大值999）
        Integer forcedReadingTime;
        //签署人是否需要免意愿快捷签署，默认false
        Boolean agreeSkipWillingness;
        //签署任务类型，默认值为 0
        Integer signTaskType;
        //签署前提示弹框自定义签署声明--文案标题（最多20字）
        String signTipsTitle;
        //签署前提示弹框自定义签署声明--文案内容（最多500字）
        String signTipsContent;
        //允许签署方在签署时上传的附件列表配置
        UploadFiles uploadFiles;
        //签署方可见文件类型，默认：1
        //1：允许查看流程内所有文件
        //2：仅允许查看自身签署的文件和指定文件（通过viewableFileIds指定文件id列表）
        Integer docsViewType;
        //指定签署方允许查看的文件id列表（仅在docsViewType为2的情况下生效）
        List<String> viewableFileIds;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class UploadFiles {
            private String uploadDescription; // 附件的标题描述
            private Boolean required; // 此附件是否必传
        }
    }


    /**
     * 个人签署方信息
     * <p>
     * 当签署主体为个人时请传此对象，psnAccount与psnId二选一传值即可
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PsnSignerInfo {

        /**
         * 个人账号标识（手机号或邮箱）用于登录e签宝官网的凭证
         */
        String psnAccount;

        /**
         * 个人账号ID
         * <p>
         * 【注】用户在e签宝注册实名后才有账号ID，账号ID获取方式请使用【查询个人认证信息】接口通过个人账号标识（手机号或邮箱）/个人用户的证件号进行查询
         */
        String psnId;

        /**
         * 个人信息
         */
        PsnInfo psnInfo;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrgSignerInfo {
        String orgId;
        String orgName;
        OrgInfo orgInfo;
        TransactorInfo transactorInfo;
    }


    /**
     * 签署区信息（设置签署方 盖章/签名/文字输入的区域）
     * 【注】单个签署方若对应多个签署区，可传多个数组
     * （整个流程中，签署区不能超过300个）
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignFields {

        /**
         * 签署区所在待签署文件ID
         * <p>
         * 【注】这里的fileId需先添加在docs数组中，否则会报错“参数错误: 文件id不在签署流程中”。
         */
        String fileId;

        /**
         * 开发者自定义业务编号
         */
        String customBizNum;

        /**
         * 签署区类型，默认值为 0。
         * <p>
         * 0 - 签章区（用于加盖印章或签名），1 - 备注区（用于添加备注文字信息）
         */
        Integer signFieldType;

        /**
         * 该签署区是否必须签署，默认值为 true
         * <p>
         * true - 是
         * <p>
         * false - 否
         * <p>
         * 场景对接说明详见：【选签（非必须签）】
         * <p>
         * 补充说明：
         * <p>
         * 签署区配置项：freeMode（是否自由签章）必须为 false - 否（默认值）该参数才生效
         * 签署区配置项：autoSign（是否后台自动落章）必须为 false - 签署页手动签章（默认值）该参数才生效
         */
        Boolean mustSign;

        /**
         * 签章区配置项（指定signFieldType为 0 - 签章区时，该参数为必传项）
         */
        NormalSignFieldConfig normalSignFieldConfig;

        /**
         * 备注区配置项
         */
        RemarkSignFieldConfig remarkSignFieldConfig;

        /**
         * 签署区/备注区的签署日期配置项
         */
        SignDateConfig signDateConfig;

        /**
         * 独立签署日期配置项
         */
        DateSignFieldConfig dateSignFieldConfig;
    }

    /**
     * 签章区配置项（指定signFieldType为 0 - 签章区时，该参数为必传项）
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NormalSignFieldConfig {

        /**
         * 是否自由签章，默认值 false
         * true - 是，false - 否
         * 补充说明：
         * 自由签章 指不限制印章、签署位置、签章样式（单页、骑缝）、和签章个数。
         * 自由签章模式下，无需传normalSignFieldConfig对象下的其他参数。
         */
        Boolean freeMode = false;

        /**
         * 是否后台自动落章，默认值 false
         * true - 后台自动落章（无感知），false - 签署页手动签章
         * 补充说明：
         * 当签署方为个人时，不支持自动签章。
         * 当签署方为机构（且非应用Id所属企业），静默签署自动落章需先经过印章授权，点击查看印章授权规则。
         * 当签署方为应用Id所属主体企业自身静默签署时，支持后台自动落章。
         */
        Boolean autoSign = false;

        /**
         * 页面是否可移动签章区，默认值 false
         * <p>
         * true - 可移动 ，false - 不可移动
         */
        Boolean movableSignField = false;
        /**
         * 指定印章ID（印章ID是e签宝SaaS官网的印章编号）
         * 【注】平台方企业自动落章场景，如不指定印章ID，则取平台默认印章
         */
        String assignedSealId;

        /**
         * 手动签章时页面可选的印章列表（印章ID是e签宝SaaS官网的印章编号）
         */
        List<String> availableSealIds;

        /**
         * 页面可选机构印章类型，默认值ALL（多项请使用英文逗号分隔）
         * {@link io.github.easy.esign.struct.constant.SealBizTypes}
         */
        String orgSealBizTypes;

        /**
         * 页面可选个人印章样式，默认值0和1（英文逗号分隔）
         */
        String psnSealStyles = "0,1";

        /**
         * 签章区尺寸（正方形的边长，单位为px）
         */
        Integer signFieldSize;

        /**
         * 签署区宽度（矩形的左右边距距离，单位为px）
         */
        Integer signFieldWidth;

        /**
         * 签署区高度（矩形的上下边距距离，单位为px）
         */
        Integer signFieldHeight;

        /**
         * 签章区样式
         * <p>
         * 1 - 单页签章，2 - 骑缝签章
         */
        Integer signFieldStyle;

        /**
         * 签章区位置信息
         */
        SignFieldPosition signFieldPosition;

    }

    /**
     * 签章区位置信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignFieldPosition {

        /**
         * 骑缝章模式选择
         * <p>
         * ALL - 全部页盖骑缝章，AssignedPages - 指定页码盖骑缝章
         */
        String acrossPageMode;

        /**
         * 签章区所在页码
         * <p>
         * 补充说明：
         * <p>
         * （1）当signFieldStyle为1即单页签章时，只能传单个页码
         * <p>
         * （2）当signFieldStyle为2即骑缝签章时，且acrossPageMode为AssignedPages即指定页码范围时，连续页码可使用'-'指定页码范围，多个页码范围用逗号分隔，例如：1-3,6-10
         */
        String positionPage;

        /**
         * 签章区所在X坐标（当signFieldStyle为2即骑缝签章时，该参数不生效，可不传值）
         * <p>
         * 【注】可选择如下方式可以确定坐标：
         * <p>
         * （1）开放平台拖章定位工具：【请点击】
         * <p>
         * （2）根据关键字辅助定位接口【请点击】
         */
        Double positionX;

        /**
         * 签章区所在Y坐标
         */
        Double positionY;
    }

    /**
     * 备注区配置项（指定signFieldType为 1 - 备注区时，该参数为必传项）
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RemarkSignFieldConfig {

        /**
         * 自由备注模式，默认值 false
         * <p>
         * true - 是，false - 否。
         * <p>
         * 补充说明：
         * <p>
         * 自由备注 指由用户选择是否备注，且不限备注位置和备注区个数。
         */
        Boolean freeMode;

        /**
         * 文字输入方式，默认值：1
         * <p>
         * 1 - 手写抄录方式，2 - 键盘输入方式
         * <p>
         * 【注】inputType=2（键盘输入方式）时，aiCheck和remarkContent参数值不生效
         */
        Integer inputType;

        /**
         * 是否开启手写抄录AI校验，默认值：0
         * <p>
         * 0 - 不开启 ，1 - 开启 AI 校验 ，2 - 强制 AI 校验
         */
        Integer aiCheck;

        /**
         * 预设待抄录信息，最多支持50个汉字（含标点符号）
         * <p>
         * 【注】inputType=1时手写抄录方式此参数必须传值
         */
        String remarkContent;

        /**
         * 备注区是否可以移动，默认值 false
         * <p>
         * true - 可移动，false - 位置固定
         */
        Boolean movableSignField;

        /**
         * 备注文字字号，默认值14px
         */
        Integer remarkFontSize;

        /**
         * 备注区高度（矩形的上下距离，单位为px）
         */
        Double signFieldHeight;

        /**
         * 备注区宽度（矩形的左右距离，单位为px）
         */
        Double signFieldWidth;


        /**
         * 备注区位置
         */
        SignFieldPosition signFieldPosition;
    }

    /**
     * 签署区/备注区的签署日期配置项
     * 补充说明：
     * 该日期是跟签署区/备注区关联的，即一个签署区/备注区需要一个签署日期匹配，且必须和签署区/备注区在同一页码
     * 当signFieldType（签署区类型）= 0（签章区）时，指定该参数
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignDateConfig {

        /**
         * 日期格式
         * yyyy年MM月dd日（默认值）
         * yyyy-MM-dd
         * yyyy/MM/dd
         * yyyy-MM-dd HH:mm:ss
         */
        String dateFormat;

        /**
         * 日期字体大小，默认值 12px
         */
        Integer fontSize;

        /**
         * 是否显示签署日期，默认值 0
         * 0 - 不显示，1 - 固定位置显示，2 - 不固定位置
         */
        Integer showSignDate;

        /**
         * 签署日期所在位置X坐标，当showSignDate为 1- 固定位置显示时生效。
         */
        Double signDatePositionX;

        /**
         * 签署日期所在位置Y坐标，当showSignDate为 1- 固定位置显示时生效。
         */
        Double signDatePositionY;
    }


    /**
     * 独立签署日期配置项
     * 补充说明：
     * 该日期是跟签署区/备注区独立的，只要保证一个用户下存在至少一个签署区/备注区即可，可以配置多个日期位置且支持和签署区/备注区不在同一页码
     * 当signFieldType（签署区类型）= 2（独立签署日期）时，指定该参数
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateSignFieldConfig {

        /**
         * 是否是后台自动落章关联的独立签署日期，默认值 false
         */
        Boolean autoSign;

        /**
         * 日期字体大小，默认值 12px
         */
        String dateFormat;

        /**
         * 日期格式
         * yyyy年MM月dd日（默认值）
         * yyyy-MM-dd
         * yyyy/MM/dd
         * yyyy-MM-dd HH:mm:ss
         */
        Integer fontSize;

        /**
         * 是否显示签署日期，默认值 0
         * 0 - 不显示，1 - 固定位置显示，2 - 不固定位置
         */
        Integer signDatePositionPage;

        /**
         * 签署日期所在位置X坐标，当showSignDate为 1- 固定位置显示时生效。
         */
        Double signDatePositionX;

        /**
         * 签署日期所在位置Y坐标，当showSignDate为 1- 固定位置显示时生效。
         */
        Double signDatePositionY;
    }
}
