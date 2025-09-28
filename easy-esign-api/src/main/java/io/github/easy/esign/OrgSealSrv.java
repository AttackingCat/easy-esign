package io.github.easy.esign;

import io.github.easy.esign.core.AbstractSrv;
import io.github.easy.esign.error.ESignException;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.seal.req.OrgAuthorizedSealReq;
import io.github.easy.esign.struct.seal.req.OrgOwnSealReq;
import io.github.easy.esign.struct.seal.resp.OrgAuthSealsResp;
import io.github.easy.esign.struct.seal.resp.OrgOwnSealResp;
import io.github.easy.esign.struct.seal.resp.OrgOwnSealsResp;
import io.github.easy.esign.utils.StrUtil;
import io.github.easy.esign.utils.UrlUtil;


/**
 * 授权认证
 */
public class OrgSealSrv extends AbstractSrv {

//    private static OrgSealSrv instance;
//
//    @Synchronized
//    public static OrgSealSrv getInstance() {
//        if (instance == null) {
//            instance = new OrgSealSrv();
//        }
//        return instance;
//    }

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
    public ESignResp<OrgOwnSealsResp> orgOwnSealList(OrgOwnSealReq req) {
        if (StrUtil.isBlank(req.getOrgId())) {
            throw new ESignException("OrgId must be not null!");
        }
        String path = "/v3/seals/org-own-seal-list" + UrlUtil.toParam(req);
        return execute().get(path, OrgOwnSealsResp.class);
    }

    /**
     * <a href="https://open.esign.cn/doc/opendoc/seal3/czrua1">查询被外部企业授权印章</a>
     */
    public ESignResp<OrgAuthSealsResp> orgAuthorizedSealList(OrgAuthorizedSealReq req) {
        if (StrUtil.isBlank(req.getOrgId())) {
            throw new ESignException("OrgId must be not null!");
        }
        String path = "/v3/seals/org-authorized-seal-list" + UrlUtil.toParam(req);
        return execute().get(path, OrgAuthSealsResp.class);
    }

}
