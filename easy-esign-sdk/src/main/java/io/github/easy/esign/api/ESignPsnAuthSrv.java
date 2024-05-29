package io.github.easy.esign.api;

import io.github.easy.esign.core.ESignManager;
import io.github.easy.esign.struct.auth.resp.AuthorizedInfoResp;
import io.github.easy.esign.struct.auth.resp.PsnIdentityInfoResp;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.auth.req.PsnAuthReq;
import io.github.easy.esign.struct.auth.resp.PsnAuthResp;
import io.github.easy.esign.struct.auth.req.PsnIdentityInfoReq;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;


/**
 * 授权认证
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ESignPsnAuthSrv {

    private static volatile ESignPsnAuthSrv INSTANCE;

    public static synchronized ESignPsnAuthSrv getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ESignPsnAuthSrv();
        }
        return INSTANCE;
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
        return ESignManager.getContext().post(path, request, PsnAuthResp.class);
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
        String path = "/v3/persons/identity-info?" + request.toParam();
        return ESignManager.getContext().get(path, PsnIdentityInfoResp.class);
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
        return ESignManager.getContext().get(path, AuthorizedInfoResp.class);
    }

}
