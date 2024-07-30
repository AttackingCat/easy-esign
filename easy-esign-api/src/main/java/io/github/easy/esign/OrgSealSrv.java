package io.github.easy.esign;

import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.seal.resp.SealInfoResp;
import lombok.Synchronized;


/**
 * 授权认证
 */
public class OrgSealSrv extends AbstractSrv {

    private static OrgSealSrv instance;

    @Synchronized
    public static OrgSealSrv getInstance() {
        if (instance == null) {
            instance = new OrgSealSrv();
        }
        return instance;
    }

    /**
     * <a href="https://open.esign.cn/doc/opendoc/seal3/picwop">...</a>
     * <p>
     * 查询指定印章详情（机构）
     *
     */
    public ESignResp<SealInfoResp> orgSealInfo(String orgId, String sealId) {
        String path = String.format("/v3/seals/org-seal-info?orgId=%s&sealId=%s", orgId, sealId);
        return execute().get(path, SealInfoResp.class);
    }
}
