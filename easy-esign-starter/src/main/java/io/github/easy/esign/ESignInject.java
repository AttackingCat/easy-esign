package io.github.easy.esign;

import io.github.easy.esign.config.ESignConfigs;
import org.springframework.beans.factory.annotation.Autowired;

public class ESignInject {
    public ESignInject(@Autowired ESignConfigs cfg) {
        ESignManager.init(cfg);
    }
}
