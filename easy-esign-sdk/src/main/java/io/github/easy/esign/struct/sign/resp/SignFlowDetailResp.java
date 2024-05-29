package io.github.easy.esign.struct.sign.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import io.github.easy.esign.struct.commen.AuthConfig;
import io.github.easy.esign.struct.commen.NoticeConfig;

import java.util.List;

/**
 * https://open.esign.cn/doc/opendoc/pdf-sign3/xxk4q6
 *
 * <p>
 * 查询签署流程详情
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignFlowDetailResp {

    private Integer signFlowStatus; // 当前流程的状态 (0-草稿, 1-签署中, 2-完成, 3-撤销, 5-过期, 7-拒签)
    private String signFlowDescription; // 签署流程描述
    private Integer rescissionStatus; // 签署流程的解约状态 (0-未解约, 1-解约中, 2-部分解约, 3-已解约)
    private List<Integer> rescissionSignFlowIds; // 对应的解约协议签署流程ID
    private Long signFlowCreateTime; // 签署流程创建时间（毫秒级时间戳格式）
    private Long signFlowStartTime; // 签署流程开启时间（毫秒级时间戳格式）
    private Long signFlowFinishTime; // 签署流程结束时间（毫秒级时间戳格式）
    private SignFlowInitiator signFlowInitiator; // 签署流程发起方
    private SignFlowConfig signFlowConfig; // 签署流程配置项
    private List<Doc> docs; // 签署文件信息
    private List<Attachment> attachments; // 附件信息
    private List<Signer> signers; // 签署方信息
    private List<Copier> copiers; // 抄送方信息

    @Data
    public static class SignFlowInitiator {
        private PsnInitiator psnInitiator; // 个人发起方信息
        private OrgInitiator orgInitiator; // 机构发起方信息
        private Transactor transactor; // 机构发起方的经办人
    }

    @Data
    public static class PsnInitiator {
        private String psnId; // 个人发起方账号ID
        private String psnName; // 个人发起方姓名
    }

    @Data
    public static class OrgInitiator {
        private String orgId; // 机构发起方账号ID
        private String orgName; // 机构发起方企业名称
    }

    @Data
    public static class Transactor {
        private String psnId; // 经办人账号ID
        private String psnName; // 经办人姓名
    }

    @Data
    public static class SignFlowConfig {
        private String signFlowTitle; // 签署流程标题
        private Boolean autoFinish; // 签署流程是否自动完结
        private Long signFlowExpireTime; // 签署截止时间
        private String notifyUrl; // 接收回调通知的Web地址
        private ChargeConfig chargeConfig; // 计费配置项
        private NoticeConfig noticeConfig; // 流程整体通知配置项
        private SignConfig signConfig; // 签署配置项
        private List<String> audioVideoTemplateId; // 预留参数
    }

    @Data
    public static class ChargeConfig {
        private Integer chargeMode; // 计费模式
        private String orderType; // 订单套餐类型
        private String barrierCode; // 订单隔离码
    }



    @Data
    public static class SignConfig {
        private String availableSignClientTypes; // 签署终端类型
        private Boolean showBatchDropSealButton; // 签署页面是否显示“一键落章”按钮
        private AuthConfig authConfig; // 流程整体认证配置项
    }



    @Data
    public static class Doc {
        private String fileId; // 签署文件ID
        private String fileName; // 签署文件名称
        private String fileEditPwd; // 文件编辑密码
        private String contractNum; // 合同编号
    }

    @Data
    public static class Attachment {
        private String fileId; // 附件文件ID
        private String fileName; // 附件名称
        private Boolean signerUpload; // 是否为签署方上传的附件
    }

    @Data
    public static class Signer {
        private PsnSigner psnSigner; // 个人签署方信息
        private OrgSigner orgSigner; // 机构签署方信息
        private Transactor transactor; // 机构经办人
        private Integer signerType; // 签署方类型
        private Integer signOrder; // 签署顺序
        private Integer signStatus; // 当前签署状态
        private Integer signTaskType; // 签署任务类型
        private List<UploadFile> uploadFiles; // 签署方在签署时上传的附件列表信息
        private List<SignField> signFields; // 签署区信息
    }

    @Data
    public static class PsnSigner {
        private String psnId; // 个人账号ID
        private String psnName; // 个人姓名
        private PsnAccount psnAccount; // 个人账号标识
    }

    @Data
    public static class OrgSigner {
        private String orgId; // 机构账号ID
        private String orgName; // 机构名称
        private PsnAccount transactor; // 经办人账号信息
    }

    @Data
    public static class PsnAccount {
        private String accountMobile; // 经办人手机号
        private String accountEmail; // 经办人邮箱号
    }

    @Data
    public static class UploadFile {
        private String uploadDescription; // 附件的标题描述
        private Boolean required; // 此附件是否必传
        private String uploadFileId; // 签署人上传的文件ID
        private String uploadFileName; // 签署人上传的文件名称
    }

    @Data
    public static class SignField {
        private String signFieldId; // 签署区ID
        private String signFieldStatus; // 当前签署区状态
        private Long statusUpdateTime; // 签署区状态更新时间
        private String failReason; // 失败原因
        private String customBizNum; // 自定义业务编号
        private String fileId; // 签署区所在文件ID
        private Integer signFieldType; // 签署区类型
        private NormalSignFieldConfig normalSignFieldConfig; // 签章区配置信息
        private RemarkSignFieldConfig remarkSignFieldConfig; // 备注区配置信息
        private SignFieldPositionBean signFieldPositionBean; // 备注区位置
        private String actualContent; // 用户页面实际签字内容
    }

    @Data
    public static class NormalSignFieldConfig {
        private Boolean freeMode; // 自由模式
        private Integer signFieldStyle; // 签章区样式
        private SignFieldPositionBean signFieldPosition; // 签章区位置信息
        private Boolean movableSignField; // 是否可以移动签章区
        private Boolean autoSign; // 是否自动签署
        private String sealStyle; // 印章样式
        private String sealId; // 印章ID
        private String sealOwnerId; // 印章归属方用户账号ID
        private String handWrittenFileKey; // 手绘图片文件filekey
    }

    @Data
    public static class RemarkSignFieldConfig {
        private Integer inputType; // 备注文字输入方式
        private Boolean movableSignField; // 是否可以移动备注区
        private Boolean freeMode; // 自由模式
        private String remarkContent; // 备注文字预设置内容
        private String remarkImageFileKey; // 备注区生成的图片文件filekey
    }

    @Data
    public static class SignFieldPositionBean {
        private String positionPage; // 备注区所在页码
        private Float positionX; // 备注区所在X坐标
        private Float positionY; // 备注区所在Y坐标
    }

    @Data
    public static class Copier {
        private CopierPsnInfo copierPsnInfo; // 个人抄送方信息
        private CopierOrgInfo copierOrgInfo; // 机构抄送方信息
    }

    @Data
    public static class CopierPsnInfo {
        private String psnId; // 个人抄送方账号
        private String psnAccount; // 个人抄送方账号，手机号或邮箱
    }

    @Data
    public static class CopierOrgInfo {
        private String orgId; // 机构账号ID
        private String orgName; // 机构抄送方名称
    }

}

