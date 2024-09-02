package io.github.easy.esign;

import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.auth.req.OrgAuthReq;
import io.github.easy.esign.struct.auth.req.OrgIdentityInfoReq;
import io.github.easy.esign.struct.auth.resp.AuthorizedInfoResp;
import io.github.easy.esign.struct.auth.resp.OrgAuthResp;
import io.github.easy.esign.struct.auth.resp.OrgIdentityInfoResp;
import io.github.easy.esign.utils.UrlUtil;


/**
 * 授权认证
 */
public class OrgAuthSrv extends AbstractSrv {

//    private static OrgAuthSrv instance;
//
//    @Synchronized
//    public static OrgAuthSrv getInstance() {
//        if (instance == null) {
//            instance = new OrgAuthSrv();
//        }
//        return instance;
//    }

    /**
     * 获取机构认证&授权页面链接
     */
    public ESignResp<OrgAuthResp> authUrl(OrgAuthReq request) {
        String path = "/v3/org-auth-url";
        return execute().post(path, request, OrgAuthResp.class);
    }

    /**
     * 查询机构认证信息
     */
    public ESignResp<OrgIdentityInfoResp> identityInfo(OrgIdentityInfoReq request) {
        String path = "/v3/organizations/identity-info" + UrlUtil.toParam(request);
        return execute().get(path, OrgIdentityInfoResp.class);
    }


    /**
     * 查询机构授权详情
     */
    public ESignResp<AuthorizedInfoResp> authorizedInfo(String orgId) {
        String path = "/v3/organizations/" + orgId + "authorized-info";
        return execute().get(path, AuthorizedInfoResp.class);
    }
}
