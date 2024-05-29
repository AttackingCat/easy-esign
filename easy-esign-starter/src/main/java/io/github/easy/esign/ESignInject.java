package io.github.easy.esign;

import io.github.easy.esign.core.ESignManager;
import io.github.easy.esign.core.config.ESignConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class ESignInject {
    public ESignInject(@Autowired ESignConfig esignConfig) {
        ESignManager.setConfig(esignConfig);
    }
}
