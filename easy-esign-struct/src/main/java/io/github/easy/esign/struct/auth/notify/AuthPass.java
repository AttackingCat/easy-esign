package io.github.easy.esign.struct.auth.notify;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthPass {
    public String action;

    private String authFlowId;

    private Long timestamp;

    private String authType;

    private PsnInfo psnInfo;

    @Data
    @NoArgsConstructor
    public static class PsnInfo {

        private String psnId;

        private PsnAccount psnAccount;
    }

    @Data
    @NoArgsConstructor
    public static class PsnAccount {

        private String accountMobile;

        private String accountEmail;
    }
}



