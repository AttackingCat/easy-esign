package io.github.easy.esign;

import io.github.easy.esign.core.AbstractSrv;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.account.req.UnbindReq;
import io.github.easy.esign.struct.account.resp.UnbindResp;

/**
 * 账号管理API
 */
public class AccountManagementSrv extends AbstractSrv {
    /**
     * <a href=https://open.esign.cn/doc/opendoc/account_3/smelh6m20a5fizu2>解绑e签宝SaaS账号登录凭证<a/>
     */
    public ESignResp<UnbindResp> accountUnbindUrl(UnbindReq request) {
        String path = "/v3/account-unbind-url";
        return execute().post(path, request, UnbindResp.class);
    }
}
