package io.github.easy.esign;

import io.github.easy.esign.config.ESignConfigs;
import io.github.easy.esign.core.ESignManager;

public class ESignInject {
    public ESignInject(ESignConfigs cfg) {
        ESignManager.init(cfg);
    }
}
