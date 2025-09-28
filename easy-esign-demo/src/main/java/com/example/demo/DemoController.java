package com.example.demo;

import io.github.easy.esign.OrgAuthSrv;
import io.github.easy.esign.PsnAuthSrv;
import io.github.easy.esign.annotation.SwitchESignApp;
import io.github.easy.esign.struct.auth.req.OrgIdentityInfoReq;
import io.github.easy.esign.struct.auth.req.PsnIdentityInfoReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo")
@RestController
//@SwitchESignApp(adapter = CustomerESignAdapterImpl.class)
public class DemoController {
    private final OrgAuthSrv orgAuthSrv;
    private final PsnAuthSrv psnAuthSrv;

    public DemoController(OrgAuthSrv orgAuthSrv, PsnAuthSrv psnAuthSrv) {
        this.orgAuthSrv = orgAuthSrv;
        this.psnAuthSrv = psnAuthSrv;
    }

    @PostMapping("/info")
    public Object demo01(@RequestBody OrgIdentityInfoReq request) {
        return orgAuthSrv.identityInfo(request);
    }

    @PostMapping("/info2")
    public Object demo02(@RequestBody PsnIdentityInfoReq request) {
        return psnAuthSrv.identityInfo(request);
    }
}
