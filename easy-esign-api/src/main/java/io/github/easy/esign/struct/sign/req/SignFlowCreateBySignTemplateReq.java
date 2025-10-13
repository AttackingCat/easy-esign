package io.github.easy.esign.struct.sign.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <a href="https://open.esign.cn/doc/opendoc/file-and-template3/megwsgkmpbg1tec1">通过流程模板创建合同拟定和签署流程</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignFlowCreateBySignTemplateReq {


    /**
     * 流程模板ID
     */
    String signTemplateId;

    /**
     * 发起方信息（指在平台中发起合同签约的一方，合同的归属方，有权限查看签署的文件，签署通知中展示：“XXX 通知您签署... ”中的XXX即为发起方名字。）
     *
     * 发起方企业必须和流程模板ID的拥有企业一致，否则会报错：“流程模板拥有者不匹配”。
     * 不传则默认为应用ID所属的企业来发起签署流程。
     * 当指定发起方为非应用ID所属企业时，需先经过【用户授权】（代个人/企业用户发起合同签署权限）。
     */
    SignFlowInitiator signFlowInitiator;


    /**
     * 签署流程配置项
     */
    SignFlowConfig signFlowConfig;


    /**
     * 参与方信息
     *
     * 如果模板设置页面的参与方配置为：使用模板时指定，则此参数必传，需要用【查询流程模板详情】接口查询参与方ID相关信息进行参与方绑定。
     * 如果模板设置页面的参与方配置为：固定成员 和 发起人本人 时，则此参数不要指定，否则接口会报错。
     */
    List<Participants> participants;


    /**
     * 添加抄送方
     *
     * 支持场景：①抄送给个人 ②抄送给企业的接收人
     */
    List<AddCopiers> addCopiers;


    /**
     * 添加合同附件信息（指无需签名的文件，仅用于查看）
     *
     * 通过接口预设置的合同附件，发起签署页面仅限预览，无法修改删除
     */
    List<AddAttachments> addAttachments;


    /**
     * 控件列表
     *
     * 【注】用于模板控件的开发者预填内容
     */
    List<Components> components;



    /**
     * 发起方信息（指在平台中发起合同签约的一方，合同的归属方，有权限查看签署的文件，签署通知中展示：“XXX 通知您签署... ”中的XXX即为发起方名字。）
     * <p>
     * 发起方企业必须和流程模板ID的拥有企业一致，否则会报错：“流程模板拥有者不匹配”。
     * 不传则默认为应用ID所属的企业来发起签署流程。
     * 当指定发起方为非应用ID所属企业时，需先经过【用户授权】（代个人/企业用户发起合同签署权限）。
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignFlowInitiator {

        /**
         * 机构账号ID
         */
        String orgId;

        /**
         * 机构发起方的经办人
         */
        Transactor transactor;


    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Transactor {
        /**
         * 经办人账号ID
         */
        String psnId;
    }
    /**
     * 签署流程配置项
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignFlowConfig {

        /**
         * 签署任务主题
         * 【注】主题名称不可含有以下9个特殊字符：/ \ : * " < > | ？以及所有emoji表情
         */
        String signFlowTitle;

        /**
         * 签署截止时间（将展示在发起页面中，页面默认允许用户修改截止时间）
         * <p>
         * 【注】Unix时间戳格式（单位：毫秒），默认在签署流程创建后的90天时截止
         */
        Long signFlowExpireTime;

        /**
         * 合同拟定完成后是否自动开启签署流程，默认值 true
         * <p>
         * true - 自动开启
         * false - 非自动开启
         * 【注】如果有内部审批等流程需要确认填写内容是否正确，可以设置为非自动开启签署
         */
        boolean autoStart;

        /**
         * 所有签署方签署完成后签署流程自动完结，默认值 false
         * <p>
         * true - 自动完结
         * false - 非自动完结，需调用【完结签署流程】接口完结
         * 【注】设置了自动完结的流程中不允许再追加签署区、抄送方
         */
        boolean autoFinish;

        /**
         * 接收相关回调通知的Web地址
         */
        String notifyUrl;

        /**
         * 签署配置项
         */
        SignConfig signConfig;


        /**
         * 流程整体通知配置项
         */
        NoticeConfig noticeConfig;

        /**
         * 流程整体认证配置项
         */
        AuthConfig authConfig;


        /**
         * 合同相关配置项
         */
        ContractConfig contractConfig;


    }

    /**
     * 签署配置项
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignConfig {
        /**
         * 签署终端类型，默认值1和2（英文逗号分隔）
         * <p>
         * 1 - 网页（自适配H5/PC样式），2 - 支付宝
         * 【注】如果是开发者自己的app或者支付宝小程序等端内嵌e签宝H5/PC，需要传：1（网页端）
         */
        String availableSignClientTypes;

        /**
         * 若参与方需要填写的必填内容已经预填写完成（即控件均已传入预填值），是否自动跳过该参与方的填写步骤，默认值：false
         * <p>
         * true - 是（自动跳过）
         * false - 否（仍需手动提交）
         * 【注】参与方控件必须全部填写完成才能跳过
         */
        boolean autoFillAndSubmit;

        /**
         * 用户填写页面是否可以修改系统预填内容，默认值：true
         * <p>
         * true - 可修改
         * false - 不可修改
         */
        boolean editComponentValue;

    }


    /**
     * 流程整体通知配置项
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoticeConfig {

        /**
         * 通知类型，通知发起方、签署方、抄送方，默认不通知（值为""空字符串），允许多种通知方式，请使用英文逗号分隔
         * 传空 - 不通知（默认值）
         * 1 - 短信通知（如果套餐内带“分项”字样，请确保开通【电子签名流量费（分项）认证】中的子项：【短信服务】，否则短信通知收不到）
         * 2 - 邮件通知
         * 【注】个人账号中需要绑定短信/邮件才有对应的通知方式。需要绑定短信/邮件才有对应的通知方式。
         */
        String noticeTypes;

        /**
         * 通知给企业印章用印审批人员的通知类型，按照账号中的手机号或邮箱的填写情况进行通知。
         * true - 发送消息（短信+邮件+e签宝官网站内信）
         * （如果套餐内带“分项”字样，请确保开通【电子签名流量费（分项）认证】中的子项：【短信服务】，否则短信通知收不到）
         * false - 不发送消息
         * 【注】不传值默认取noticeTypes配置的通知方式
         */
        boolean examineNotice;
    }

    /**
     * 流程整体认证配置项
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthConfig {


        /**
         * 个人实名认证方式，可选值：
         * <p>
         * PSN_MOBILE3 - 个人运营商三要素认证
         * PSN_FACE - 刷脸认证
         * PSN_BANKCARD4 - 个人银行卡四要素认证
         */
        String[] psnAvailableAuthModes;

        /**
         * 机构实名认证方式，可选值：
         * <p>
         * ORG_BANK_TRANSFER - 组织机构对公账户打款认证
         * ORG_ALIPAY_CREDIT - 企业支付宝认证
         * ORG_LEGALREP_AUTHORIZATION - 组织机构授权委托书认证
         * ORG_LEGALREP - 法定代表人本人认证
         */
        String[] orgAvailableAuthModes;
    }

    /**
     * 合同相关配置项
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContractConfig {

        /**
         * 该签署流程是否允许发起解约，默认true
         * <p>
         * true - 允许
         * false - 不允许
         */
        boolean allowToRescind;
    }

    /**
     * 参与方信息
     * <p>
     * 如果模板设置页面的参与方配置为：使用模板时指定，则此参数必传，需要用【查询流程模板详情】接口查询参与方ID相关信息进行参与方绑定。
     * 如果模板设置页面的参与方配置为：固定成员 和 发起人本人 时，则此参数不要指定，否则接口会报错。
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Participants {

        /**
         * 参与方ID
         * <p>
         * 补充说明：
         * 需要用【查询流程模板详情】接口查询参与方ID
         * 通过参与方ID绑定下述参与方，绑定企业传企业参与方，绑定个人传个人参与方
         */
        String participantId;


        /**
         * 企业参与方
         */
        OrgParticipant orgParticipant;


        /**
         * 个人参与方
         */
        PsnParticipant psnParticipant;
    }


    /**
     * 企业参与方
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrgParticipant {

        /**
         * 企业ID
         * <p>
         * 【注】
         * orgName与orgId二选一传值
         * 用户在e签宝注册实名后才有账号ID，账号ID获取方式请使用【查询机构认证信息】接口通过组织机构名称/组织机构证件号进行查询
         */
        String orgId;

        /**
         * 企业名称
         * <p>
         * 【注】orgName与orgId二选一传值
         */
        String orgName;


        /**
         * 企业参与方经办人
         */
        Transactor transactor;


        /**
         * 企业参与方经办人
         */
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Transactor {

            /**
             * 经办人个人ID
             * <p>
             * 【注】
             * 当传入orgId时，transactorPsnId必传，transactorPsnAccount和transactorName不能传
             * 用户在e签宝注册实名后才有账号ID，账号ID获取方式请使用【查询个人认证信息】接口通过个人账号标识（手机号或邮箱）/个人用户的证件号进行查询
             */
            String transactorPsnId;


            /**
             * 经办人手机号/邮箱
             * 【注】当传入orgName时，transactorPsnAccount、transactorName必传，transactorPsnId不能传
             */
            String transactorPsnAccount;


            /**
             * 经办人姓名
             */
            String transactorName;

        }
    }




    /**
     * 个人参与方
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PsnParticipant {
        /**
         * 个人ID
         * <p>
         * 【注】
         * <p>
         * psnAccount与psnId二选一传值
         * 用户在e签宝注册实名后才有账号ID，账号ID获取方式请使用【查询个人认证信息】接口通过个人账号标识（手机号或邮箱）/个人用户的证件号进行查询
         */
        String psnId;

        /**
         * 个人手机号/邮箱
         * <p>
         * 【注】psnAccount与psnId二选一传值
         */
        String psnAccount;

        /**
         * 个人姓名
         * <p>
         * 【注】当传入psnAccount时，psnName必传；当传入psnId时，psnName不能传。
         */
        String psnName;

    }


    /**
     * 添加抄送方
     * 支持场景：①抄送给个人 ②抄送给企业的接收人
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddCopiers {

        /**
         * 个人抄送方信息
         */
        CopierPsnInfo copierPsnInfo;
        /**
         * 机构抄送方信息
         */
        CopierOrgInfo copierOrgInfo;


    }


    /**
     * 个人抄送方信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CopierPsnInfo {

        /**
         * 个人抄送方ID（若已知用户的psnId，请传此参数）
         */
        String psnId;

        /**
         * 个人抄送方账号，手机号或邮箱（若未知用户的psnId，请传此参数）
         */
        String psnAccount;

        /**
         * 个人抄送方姓名（若未知用户的psnId，请传此参数）
         */
        String psnName;

    }

    /**
     * 机构抄送方信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CopierOrgInfo {


        /**
         * 机构抄送方ID（若已知机构的orgId，请传此参数）
         */
        String orgId;


        /**
         * 机构抄送方名称（若未知机构的orgId，请传此参数）
         */
        String orgName;
    }


    /**
     * 添加合同附件信息（指无需签名的文件，仅用于查看）
     * <p>
     * 通过接口预设置的合同附件，发起签署页面仅限预览，无法修改删除
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddAttachments {
        /**
         * 合同附件的文件ID
         */
        String fileId;

        /**
         * 合同附件名称
         * <p>
         * 【注】名称不可含有以下9个特殊字符：/ \ : * " < > | ？以及所有emoji表情
         */
        String fileName;

    }

    /**
     * 控件列表
     *
     * 【注】用于模板控件的开发者预填内容
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Components {

        /**
         * 控件所属文件ID
         */
        String fileId;

        /**
         * 控件ID（控件ID和控件key二选一）
         */
        String componentId;

        /**
         * 控件key（控件ID和控件key二选一）
         */
        String componentKey;

        /**
         * 控件填充值
         */
        String componentValue;
    }

}