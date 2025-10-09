package io.github.easy.esign;

import io.github.easy.esign.config.ESignConfigs;
import io.github.easy.esign.core.ESignManager;

public class ESignInject {
    public ESignInject(ESignConfigs cfg) {
        if (cfg == null) {
            throw new IllegalArgumentException("ESignConfigs cannot be null");
        }
        if (cfg.getConfigs() == null || cfg.getConfigs().isEmpty()) {
            return;
        }
        ESignManager.loadConfigs(cfg);
    }
}