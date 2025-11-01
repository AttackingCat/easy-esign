package io.github.easy.esign.autoconfigure;

import io.github.easy.esign.*;
import io.github.easy.esign.annotation.SwitchESignApp;
import io.github.easy.esign.annotation.SwitchESignAppInterceptor;
import io.github.easy.esign.config.ESignConfigs;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * e签宝自动配置
 */
@AutoConfiguration
public class ESignAutoConfiguration {

    @Bean
    public DefaultPointcutAdvisor switchESignAppAdvisor(ApplicationContext context) {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        // 支持类级别和方法级别的注解，方法级别优先级更高
        advisor.setPointcut(new AnnotationMatchingPointcut(SwitchESignApp.class, SwitchESignApp.class));
        advisor.setAdvice(new SwitchESignAppInterceptor(context));
        return advisor;
    }

    @Bean
    @ConfigurationProperties(prefix = "esign-v3", ignoreInvalidFields = true)
    @ConditionalOnMissingBean
    public ESignConfigs eSignConfig() {
        return new ESignConfigs();
    }

    @Bean
    @ConditionalOnBean(ESignConfigs.class)
    @ConditionalOnMissingBean
    public ESignInject eSignInject(ESignConfigs cfg) {
        return new ESignInject(cfg);
    }

    @Bean
    @ConditionalOnMissingBean
    public FileSrv fileSrv() {
        return new FileSrv();
    }

    @Bean
    @ConditionalOnMissingBean
    public AccountManagementSrv userSrv() {
        return new AccountManagementSrv();
    }

    @Bean
    @ConditionalOnMissingBean
    public OrgAuthSrv orgAuthSrv() {
        return new OrgAuthSrv();
    }

    @Bean
    @ConditionalOnMissingBean
    public OrgSealSrv orgSealSrv() {
        return new OrgSealSrv();
    }

    @Bean
    @ConditionalOnMissingBean
    public PsnAuthSrv psnAuthSrv() {
        return new PsnAuthSrv();
    }

    @Bean
    @ConditionalOnMissingBean
    public SignFlowSrv signFlowSrv() {
        return new SignFlowSrv();
    }
}
