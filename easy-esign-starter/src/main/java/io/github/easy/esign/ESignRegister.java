package io.github.easy.esign;

import io.github.easy.esign.annotation.SwitchESignApp;
import io.github.easy.esign.annotation.SwitchESignAppInterceptor;
import io.github.easy.esign.config.ESignConfigs;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

public class ESignRegister {

    /**
     * 获取配置Bean
     *
     * @return 配置对象
     */

    @Bean
    public DefaultPointcutAdvisor switchESignAppAdvisor(@Autowired ApplicationContext context) {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(SwitchESignApp.class);
        SwitchESignAppInterceptor interceptor = new SwitchESignAppInterceptor(context);
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
        return new DocTemplateSrv();
    }

    @Bean
    public FileSrv FileSrv() {
        return new FileSrv();
    }

    @Bean
    public OrgAuthSrv OrgAuthSrv() {
        return new OrgAuthSrv();
    }

    @Bean
    public OrgSealSrv OrgSealSrv() {
        return new OrgSealSrv();
    }

    @Bean
    public PsnAuthSrv PsnAuthSrv() {
        return new PsnAuthSrv();
    }

    @Bean
    public SignFlowSrv SignFlowSrv() {
        return new SignFlowSrv();
    }
}
