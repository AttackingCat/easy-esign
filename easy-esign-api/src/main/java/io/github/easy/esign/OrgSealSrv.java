package io.github.easy.esign;

import io.github.easy.esign.error.ESignException;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.constant.SealBizTypes;
import io.github.easy.esign.struct.seal.resp.OrgAuthSealsResp;
import io.github.easy.esign.struct.seal.resp.OrgOwnSealResp;
import io.github.easy.esign.struct.seal.resp.OrgOwnSealsResp;
import io.github.easy.esign.utils.StrUtil;
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
     * <a href="https://open.esign.cn/doc/opendoc/seal3/picwop">查询指定印章详情（机构）</a>
     */
    public ESignResp<OrgOwnSealResp> orgSealInfo(String orgId, String sealId) {
        String path = String.format("/v3/seals/org-seal-info?orgId=%s&sealId=%s", orgId, sealId);
        return execute().get(path, OrgOwnSealResp.class);
    }

    /**
     * <a href="https://open.esign.cn/doc/opendoc/seal3/ups6h1">查询企业内部印章</a>
     */
    public ESignResp<OrgOwnSealsResp> orgOwnSealList(String orgId, Integer pageNum, Integer pageSize, SealBizTypes sealBizType) {
        if (StrUtil.isBlank(orgId)) {
            throw new ESignException("OrgId must be not null!");
        }
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        String path = String.format("/v3/seals/org-own-seal-list?orgId=%s&pageNum=%s&pageSize=%s", orgId, pageNum, pageSize);
        if (sealBizType != null) {
            path += "&sealBizType=" + sealBizType.name();
        }
        return execute().get(path, OrgOwnSealsResp.class);
    }

    /**
     * <a href="https://open.esign.cn/doc/opendoc/seal3/czrua1">查询被外部企业授权印章</a>
     */
    public ESignResp<OrgAuthSealsResp> orgAuthorizedSealList(String orgId, Integer pageNum, Integer pageSize, String authorizerOrgId) {
        if (StrUtil.isBlank(orgId)) {
            throw new ESignException("OrgId must be not null!");
        }
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        String path = String.format("/v3/seals/org-authorized-seal-list?orgId=%s&pageNum=%s&pageSize=%s", orgId, pageNum, pageSize);
        if (StrUtil.isBlank(authorizerOrgId)) {
            path += "&authorizerOrgId=" + authorizerOrgId;
        }
        return execute().get(path, OrgAuthSealsResp.class);
    }
}
