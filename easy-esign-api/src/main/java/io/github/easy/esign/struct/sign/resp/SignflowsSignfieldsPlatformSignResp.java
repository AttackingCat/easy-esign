package io.github.easy.esign.struct.sign.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * https://open.esign.cn/doc/opendoc/saas_api/olgmg0_xi1khu
 * <p>
 * 添加平台方自动盖章签署区
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignflowsSignfieldsPlatformSignResp {

    /**
     * 签署区列表数据
     */
    List<SignfieldBeans> signfieldBeans;

    /**
     * 签署区列表数据
     */
    @Data
    public static class SignfieldBeans {

        /**
         * 用户ID
         */
        String accountId;

        /**
         * 文件file id
         */
        String fileId;

        /**
         * 签署区id
         */
        String signfieldId;

    }

}
