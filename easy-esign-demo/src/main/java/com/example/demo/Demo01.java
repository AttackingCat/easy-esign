package com.example.demo;

import io.github.easy.esign.core.api.OrgAuthSrv;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.auth.req.OrgIdentityInfoReq;
import io.github.easy.esign.struct.auth.resp.OrgIdentityInfoResp;
import io.github.easy.esign.struct.constant.OrgIDCardType;

public class Demo01 {
    public static void main(String[] args) {
        OrgIdentityInfoReq orgIdentityInfoReq = new OrgIdentityInfoReq();
        orgIdentityInfoReq.setOrgIDCardNum("910000622326970013");
        orgIdentityInfoReq.setOrgIDCardType(OrgIDCardType.CRED_ORG_USCC);
        OrgAuthSrv signOrgAuthSrv = OrgAuthSrv.getInstance();
        ESignResp<OrgIdentityInfoResp> orgIdentityInfoResponseESignResp = signOrgAuthSrv.identityInfo(orgIdentityInfoReq);
        System.out.println(orgIdentityInfoResponseESignResp);
    }
}
