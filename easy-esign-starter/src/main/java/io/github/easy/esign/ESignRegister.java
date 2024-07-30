package io.github.easy.esign;

import io.github.easy.esign.annotation.SwitchESignApp;
import io.github.easy.esign.annotation.SwitchESignAppInterceptor;
import io.github.easy.esign.config.ESignConfigs;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
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
    @ConditionalOnBean(ESignConfigs.class)
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
    public ESignInject ESignInject(ESignConfigs cfg) {
        return new ESignInject(cfg);
    }

    @Bean
    public DocTemplateSrv DocTemplateSrv() {
        return DocTemplateSrv.getInstance();
    }

    @Bean
    public FileSrv FileSrv() {
        return FileSrv.getInstance();
    }

    @Bean
    public OrgAuthSrv OrgAuthSrv() {
        return OrgAuthSrv.getInstance();
    }

    @Bean
    public OrgSealSrv OrgSealSrv() {
        return OrgSealSrv.getInstance();
    }

    @Bean
    public PsnAuthSrv PsnAuthSrv() {
        return PsnAuthSrv.getInstance();
    }

    @Bean
    public SignFlowSrv SignFlowSrv() {
        return SignFlowSrv.getInstance();
    }

}
