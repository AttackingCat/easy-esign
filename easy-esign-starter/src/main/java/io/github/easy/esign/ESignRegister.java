package io.github.easy.esign;

import io.github.easy.esign.core.api.DocTemplateSrv;
import io.github.easy.esign.core.api.*;
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
    public DocTemplateSrv DocTemplateSrv() {
        return DocTemplateSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfig.class)
    public FileSrv FileSrv() {
        return FileSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfig.class)
    public OrgAuthSrv OrgAuthSrv() {
        return OrgAuthSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfig.class)
    public OrgSealSrv OrgSealSrv() {
        return OrgSealSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfig.class)
    public PsnAuthSrv PsnAuthSrv() {
        return PsnAuthSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfig.class)
    public SignFlowSrv SignFlowSrv() {
        return SignFlowSrv.getInstance();
    }
}
