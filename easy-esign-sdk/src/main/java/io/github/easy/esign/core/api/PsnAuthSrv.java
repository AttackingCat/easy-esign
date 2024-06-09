package io.github.easy.esign.core.api;

import io.github.easy.esign.core.BaseExecute;
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

    private static final BaseExecute execute = getExecute(DocTemplateSrv.class);
    private static PsnAuthSrv instance;

    @Synchronized
    public static PsnAuthSrv getInstance() {
        if (instance == null) {
            instance = new PsnAuthSrv();
        }
        return instance;
    }

    /**
     * https://open.esign.cn/doc/opendoc/auth3/rx8igf
     * <p>
     * 获取个人认证&授权页面链接
     *
     * @param request
     * @return
     */
    public ESignResp<PsnAuthResp> authUrl(PsnAuthReq request) {
        String path = "/v3/psn-auth-url";
        return execute.post(path, request, PsnAuthResp.class);
    }

    /**
     * https://open.esign.cn/doc/opendoc/auth3/vssvtu
     * <p>
     * 查询个人认证信息
     *
     * @param request
     * @return
     */
    public ESignResp<PsnIdentityInfoResp> identityInfo(PsnIdentityInfoReq request) {
        String path = "/v3/persons/identity-info" +  UrlUtil.toParam(request);
        return execute.get(path, PsnIdentityInfoResp.class);
    }


    /**
     * https://open.esign.cn/doc/opendoc/auth3/nurtvw
     * <p>
     * 查询个人用户授权详情
     *
     * @param psnId
     * @return
     */
    public ESignResp<AuthorizedInfoResp> authorizedInfo(String psnId) {
        String path = "/v3/persons/" + psnId + "authorized-info";
        return execute.get(path, AuthorizedInfoResp.class);
    }

}
