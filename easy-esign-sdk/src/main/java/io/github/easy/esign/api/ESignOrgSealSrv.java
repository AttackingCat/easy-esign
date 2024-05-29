package io.github.easy.esign.api;

import io.github.easy.esign.core.ESignManager;
import io.github.easy.esign.struct.seal.resp.SealInfoResp;
import io.github.easy.esign.struct.ESignResp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;


/**
 * 授权认证
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ESignOrgSealSrv {

    private static volatile ESignOrgSealSrv INSTANCE;

    public static synchronized ESignOrgSealSrv getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ESignOrgSealSrv();
        }
        return INSTANCE;
    }

    /**
     * https://open.esign.cn/doc/opendoc/seal3/picwop
     * <p>
     * 查询指定印章详情（机构）
     *
     * @param orgId
     * @return
     */
    public ESignResp<SealInfoResp> orgSealInfo(String orgId, String sealId) {
        String path = String.format("/v3/seals/org-seal-info?orgId=%s&sealId=%s", orgId, sealId);
        return ESignManager.getContext().get(path, SealInfoResp.class);
    }
}
