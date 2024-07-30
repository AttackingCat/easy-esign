package io.github.easy.esign;

import io.github.easy.esign.config.ESignConfigs;

public class ESignInject {
    public ESignInject(ESignConfigs cfg) {
        ESignManager.init(cfg);
    }
}
