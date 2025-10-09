package io.github.easy.esign;

import io.github.easy.esign.core.AbstractSrv;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.auth.req.PsnAuthReq;
import io.github.easy.esign.struct.auth.req.PsnIdentityInfoReq;
import io.github.easy.esign.struct.auth.resp.AuthorizedInfoResp;
import io.github.easy.esign.struct.auth.resp.PsnAuthResp;
import io.github.easy.esign.struct.auth.resp.PsnIdentityInfoResp;
import io.github.easy.esign.utils.UrlUtil;


/**
 * 授权认证
 */
public class PsnAuthSrv extends AbstractSrv {

    /**
     * <a href="https://open.esign.cn/doc/opendoc/auth3/rx8igf">获取个人认证&授权页面链接</a>
     */
    public ESignResp<PsnAuthResp> authUrl(PsnAuthReq request) {
        String path = "/v3/psn-auth-url";
        return execute().post(path, request, PsnAuthResp.class);
    }

    /**
     * <a href="https://open.esign.cn/doc/opendoc/auth3/vssvtu">查询个人认证信息</a>
     */
    public ESignResp<PsnIdentityInfoResp> identityInfo(PsnIdentityInfoReq request) {
        String path = "/v3/persons/identity-info" + UrlUtil.toParam(request);
        return execute().get(path, PsnIdentityInfoResp.class);
    }


    /**
     * <a href="https://open.esign.cn/doc/opendoc/auth3/nurtvw">查询个人用户授权详情</a>
     */
    public ESignResp<AuthorizedInfoResp> authorizedInfo(String psnId) {
        String path = "/v3/persons/" + psnId + "authorized-info";
        return execute().get(path, AuthorizedInfoResp.class);
    }

}
