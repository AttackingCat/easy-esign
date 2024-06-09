package io.github.easy.esign.core.api;

import io.github.easy.esign.core.BaseExecute;
import io.github.easy.esign.core.api.abs.SrvTemp;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.auth.resp.AuthorizedInfoResp;
import io.github.easy.esign.struct.auth.resp.OrgAuthResp;
import io.github.easy.esign.struct.auth.resp.OrgIdentityInfoResp;
import io.github.easy.esign.struct.auth.req.OrgAuthReq;
import io.github.easy.esign.struct.auth.req.OrgIdentityInfoReq;
import io.github.easy.esign.utils.UrlUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Synchronized;


/**
 * 授权认证
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrgAuthSrv extends SrvTemp {

    private static final BaseExecute execute = getExecute(OrgAuthSrv.class);
    private static OrgAuthSrv instance;

    @Synchronized
    public static OrgAuthSrv getInstance() {
        if (instance == null) {
            instance = new OrgAuthSrv();
        }
        return instance;
    }

    /**
     * https://open.esign.cn/doc/opendoc/auth3/kcbdu7
     * <p>
     * 获取机构认证&授权页面链接
     *
     * @param request
     * @return
     */
    public ESignResp<OrgAuthResp> authUrl(OrgAuthReq request) {
        String path = "/v3/org-auth-url";
        return execute.post(path, request, OrgAuthResp.class);
    }

    /**
     * https://open.esign.cn/doc/opendoc/auth3/vssvtu
     * <p>
     * 查询机构认证信息
     *
     * @param request
     * @return
     */
    public ESignResp<OrgIdentityInfoResp> identityInfo(OrgIdentityInfoReq request) {
        String path = "/v3/organizations/identity-info" + UrlUtil.toParam(request);
        return execute.get(path, OrgIdentityInfoResp.class);
    }


    /**
     * https://open.esign.cn/doc/opendoc/auth3/ytn2tt
     * <p>
     * 查询机构授权详情
     *
     * @param orgId
     * @return
     */
    public ESignResp<AuthorizedInfoResp> authorizedInfo(String orgId) {
        String path = "/v3/organizations/" + orgId + "authorized-info";
        return execute.get(path, AuthorizedInfoResp.class);
    }

}
