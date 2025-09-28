package io.github.easy.esign.autoconfigure;

import io.github.easy.esign.adapter.DefaultESignAppNameAdapter;
import io.github.easy.esign.adapter.ESignAppNameAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认 Adapter 配置
 */
@Configuration(proxyBeanMethods = false)
public class ESignAppNameAdapterConfig {

    @Bean
    @ConditionalOnMissingBean(ESignAppNameAdapter.class)
    public ESignAppNameAdapter eSignAppNameAdapter() {
        return new DefaultESignAppNameAdapter();
    }
}
