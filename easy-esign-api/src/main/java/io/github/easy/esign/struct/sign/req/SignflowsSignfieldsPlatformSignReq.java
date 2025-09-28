package io.github.easy.esign.struct.sign.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * https://open.esign.cn/doc/opendoc/saas_api/olgmg0_xi1khu
 * <p>
 * 添加平台方自动盖章签署区
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignflowsSignfieldsPlatformSignReq {

    /**
     * 签署流程ID
     */
    String signFlowId;

    /**
     * 签署区列表数据
     */
    List<Signfields> signfields;

    /**
     * 签署区列表数据
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Signfields {

        /**
         * 文件file id
         */
        String fileId;

        /**
         * 签署顺序，默认1,且不小于1，顺序越小越先处理
         */
        Integer order;

        /**
         * 签署类型， 1-单页签署，2-骑缝签署，默认1
         */
        Integer signType;

        /**
         * 签署区位置信息
         */
        PosBean posBean;

        /**
         * 是否需要添加签署日期，0-禁止 1-必须 2-不限制，默认0
         */
        Integer signDateBeanType;

        /**
         * 印章ID
         * <p>
         * 仅限企业章，暂不支持指定企业法定代表人印章
         * <p>
         * 注：
         * <p>
         * （1）当印章ID为空时，取appId对应企业的默认印章；
         * <p>
         * （2）如果指定企业授权印章，签署后的签名信息，印章样式和数字证书均为授权企业主体所有，详细参考【印章授权说明】
         */
        String sealId;

        /**
         * 第三方业务流水号id，保证相同签署人、相同签约主体、相同签署顺序的任务，对应的第三方业务流水id唯一，默认空
         * <p>
         * 如果传了该参数，【签署人签署完成异步通知】中的thirdOrderNo参数会取这里的值
         */
        String thirdOrderNo;

        /**
         * 签署区位置信息,
         * <p>
         * （当signType为1时, 页码和XY坐标不能为空；
         * <p>
         * 当signType为2时, 页码和Y坐标不能为空）
         */
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class PosBean {

            /**
             * 页码信息，
             * <p>
             * 当签署区signType为2时, 页码可以'-'分割, 传all代表盖全部页码；
             * <p>
             * 其他情况只能是数字
             */
            String posPage;

            /**
             * x坐标，默认空
             */
            Double posX;

            /**
             * y坐标
             */
            Double posY;

            /**
             * 签署区宽，默认印章宽度
             */
            Integer width;

            /**
             * 签章日期，默认跟随在印章底部，默认false。
             * <p>
             * true-显示日期
             * <p>
             * false-不显示日期
             */
            Boolean addSignTime;

            /**
             * 签章日期格式，yyyy-MM-dd HH:mm:ss
             */
            String signTimeFormat;
        }
    }

}
