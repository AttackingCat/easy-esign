package com.example.demo;

import io.github.easy.esign.OrgAuthSrv;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.auth.req.OrgIdentityInfoReq;
import io.github.easy.esign.struct.auth.resp.OrgIdentityInfoResp;
import io.github.easy.esign.struct.constant.OrgIDCardType;

public class Demo01 {
    public void test() {
        OrgIdentityInfoReq orgIdentityInfoReq = new OrgIdentityInfoReq();
        orgIdentityInfoReq.setOrgIDCardNum("910000622326970013");
        orgIdentityInfoReq.setOrgIDCardType(OrgIDCardType.CRED_ORG_USCC);
        OrgAuthSrv signOrgAuthSrv = new OrgAuthSrv();
        ESignResp<OrgIdentityInfoResp> resp = signOrgAuthSrv.identityInfo(orgIdentityInfoReq);
        System.out.println(resp);
    }

}
