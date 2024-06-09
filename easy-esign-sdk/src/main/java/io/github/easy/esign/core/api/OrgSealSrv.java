package io.github.easy.esign.core.api;

import io.github.easy.esign.core.BaseExecute;
import io.github.easy.esign.core.api.abs.SrvTemp;
import io.github.easy.esign.struct.seal.resp.SealInfoResp;
import io.github.easy.esign.struct.ESignResp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Synchronized;


/**
 * 授权认证
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrgSealSrv extends SrvTemp {

    private static final BaseExecute execute = getExecute(DocTemplateSrv.class);
    private static OrgSealSrv instance;

    @Synchronized
    public static OrgSealSrv getInstance() {
        if (instance == null) {
            instance = new OrgSealSrv();
        }
        return instance;
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
        return execute.get(path, SealInfoResp.class);
    }
}