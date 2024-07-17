package io.github.easy.esign.core.api;

import io.github.easy.esign.core.Execute;
import io.github.easy.esign.core.api.abs.SrvTemp;
import io.github.easy.esign.struct.auth.resp.AuthorizedInfoResp;
import io.github.easy.esign.struct.auth.resp.PsnIdentityInfoResp;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.auth.req.PsnAuthReq;
import io.github.easy.esign.struct.auth.resp.PsnAuthResp;
import io.github.easy.esign.struct.auth.req.PsnIdentityInfoReq;
import io.github.easy.esign.utils.UrlUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Synchronized;


/**
 * 授权认证
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PsnAuthSrv extends SrvTemp {

    private static PsnAuthSrv instance;

    @Synchronized
    public static PsnAuthSrv getInstance() {
        if (instance == null) {
            instance = new PsnAuthSrv();
        }
        return instance;
    }

    /**
     * <a href="https://open.esign.cn/doc/opendoc/auth3/rx8igf">...</a>
     * <p>
     * 获取个人认证&授权页面链接
     *
     */
    public ESignResp<PsnAuthResp> authUrl(PsnAuthReq request) {
        String path = "/v3/psn-auth-url";
        return execute().post(path, request, PsnAuthResp.class);
    }

    /**
     * <a href="https://open.esign.cn/doc/opendoc/auth3/vssvtu">...</a>
     * <p>
     * 查询个人认证信息
     *
     */
    public ESignResp<PsnIdentityInfoResp> identityInfo(PsnIdentityInfoReq request) {
        String path = "/v3/persons/identity-info" + UrlUtil.toParam(request);
        return execute().get(path, PsnIdentityInfoResp.class);
    }


    /**
     * <a href="https://open.esign.cn/doc/opendoc/auth3/nurtvw">...</a>
     * <p>
     * 查询个人用户授权详情
     *
     */
    public ESignResp<AuthorizedInfoResp> authorizedInfo(String psnId) {
        String path = "/v3/persons/" + psnId + "authorized-info";
        return execute().get(path, AuthorizedInfoResp.class);
    }

}
