package io.github.easy.esign;

import io.github.easy.esign.annotation.SwitchESignApp;
import io.github.easy.esign.annotation.SwitchESignAppInterceptor;
import io.github.easy.esign.api.*;
import io.github.easy.esign.core.config.ESignConfigs;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class ESignConfigRegister {

    /**
     * 获取配置Bean
     *
     * @return 配置对象
     */

    @Bean
    public DefaultPointcutAdvisor switchESignAppAdvisor() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(SwitchESignApp.class);
        SwitchESignAppInterceptor interceptor = new SwitchESignAppInterceptor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }

    @Bean
    @ConfigurationProperties(prefix = "esign-v3", ignoreInvalidFields = true)
    public ESignConfigs eSignConfig() {
        return new ESignConfigs();
    }

    @Bean
    @ConditionalOnBean(ESignConfigs.class)
    public DocTemplateAbstractSrv DocTemplateSrv() {
        return DocTemplateAbstractSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfigs.class)
    public FileAbstractSrv FileSrv() {
        return FileAbstractSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfigs.class)
    public OrgAuthAbstractSrv OrgAuthSrv() {
        return OrgAuthAbstractSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfigs.class)
    public OrgSealAbstractSrv OrgSealSrv() {
        return OrgSealAbstractSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfigs.class)
    public PsnAuthAbstractSrv PsnAuthSrv() {
        return PsnAuthAbstractSrv.getInstance();
    }

    @Bean
    @ConditionalOnBean(ESignConfigs.class)
    public SignFlowAbstractSrv SignFlowSrv() {
        return SignFlowAbstractSrv.getInstance();
    }

}
