package io.github.easy.esign;

import io.github.easy.esign.api.*;
import io.github.easy.esign.core.config.ESignConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class ESignRegister {

    /**
     * 获取配置Bean
     *
     * @return 配置对象
     */
    @Bean
    @ConfigurationProperties(prefix = "esign-v3", ignoreInvalidFields = true)
    public ESignConfig eSignConfig() {
        return new ESignConfig();
    }

    @Bean
    @ConditionalOnBean(ESignConfig.class)
    public ESignDocTemplateSrv eSignDocTemplateSrv() {
        return ESignDocTemplateSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfig.class)
    public ESignFileSrv eSignFileSrv() {
        return ESignFileSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfig.class)
    public ESignOrgAuthSrv eSignOrgAuthSrv() {
        return ESignOrgAuthSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfig.class)
    public ESignOrgSealSrv eSignOrgSealSrv() {
        return ESignOrgSealSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfig.class)
    public ESignPsnAuthSrv eSignPsnAuthSrv() {
        return ESignPsnAuthSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfig.class)
    public ESignSignFlowSrv eSignSignFlowSrv() {
        return ESignSignFlowSrv.getInstance();
    }
}
