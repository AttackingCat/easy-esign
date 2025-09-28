package io.github.easy.esign.autoconfigure;

import io.github.easy.esign.ESignInject;
import io.github.easy.esign.ESignServiceRegistrar;
import io.github.easy.esign.annotation.SwitchESignApp;
import io.github.easy.esign.annotation.SwitchESignAppInterceptor;
import io.github.easy.esign.config.ESignConfigs;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
        advisor.setPointcut(new AnnotationMatchingPointcut(SwitchESignApp.class));
        advisor.setAdvice(new SwitchESignAppInterceptor(context));
        return advisor;
    }

    @Bean
    @ConfigurationProperties(prefix = "esign-v3", ignoreInvalidFields = true)
    public ESignConfigs eSignConfig() {
        return new ESignConfigs();
    }

    @Bean
    @ConditionalOnBean(ESignConfigs.class)
    public ESignInject eSignInject(ESignConfigs cfg) {
        return new ESignInject(cfg);
    }

    /**
     * 动态注册所有 Srv 类
     */
    @Bean
    public static ESignServiceRegistrar eSignServiceRegistrar() {
        return new ESignServiceRegistrar("io.github.easy.esign");
    }
}
