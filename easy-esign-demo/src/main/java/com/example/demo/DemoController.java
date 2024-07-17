package com.example.demo;

import io.github.easy.esign.core.api.OrgAuthSrv;
import io.github.easy.esign.core.api.PsnAuthSrv;
import io.github.easy.esign.struct.auth.req.OrgIdentityInfoReq;
import io.github.easy.esign.struct.auth.req.PsnIdentityInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo")
@RestController
public class DemoController {
    private final OrgAuthSrv orgAuthSrv;
    private final PsnAuthSrv psnAuthSrv;

    public DemoController(OrgAuthSrv orgAuthSrv, PsnAuthSrv psnAuthSrv) {
        this.orgAuthSrv = orgAuthSrv;
        this.psnAuthSrv = psnAuthSrv;
    }

    @GetMapping("/info")
    public Object demo01(@RequestBody OrgIdentityInfoReq request) {
        return orgAuthSrv.identityInfo(request);
    }

    @GetMapping("/info2")
    public Object demo02(@RequestBody PsnIdentityInfoReq request) {
        return psnAuthSrv.identityInfo(request);
    }
}
