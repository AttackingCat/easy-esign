package io.github.easy.esign.annotation;

import io.github.easy.esign.core.ESignManager;
import io.github.easy.esign.adapter.ESignAppNameAdapter;
import io.github.easy.esign.utils.StrUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

public class SwitchESignAppInterceptor implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(SwitchESignAppInterceptor.class);

    private final ApplicationContext context;

    public SwitchESignAppInterceptor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object invoke(@NotNull MethodInvocation invocation) throws Throwable {
        SwitchESignApp switchESignApp = findAnnotation(invocation);
        if (switchESignApp == null) {
            return invocation.proceed();
        }

        String appName = determineAppName(switchESignApp);
        try {
            ESignManager.switchExecute(appName);
            return invocation.proceed();
        } finally {
            ESignManager.clearExecute();
        }
    }

    private SwitchESignApp findAnnotation(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        SwitchESignApp annotation = method.getAnnotation(SwitchESignApp.class);
        if (annotation == null) {
            Class<?> targetClass = Objects.requireNonNull(invocation.getThis()).getClass();
            annotation = targetClass.getAnnotation(SwitchESignApp.class);
        }
        return annotation;
    }

    private String determineAppName(SwitchESignApp switchESignApp) {
        String appName = StrUtil.isNotBlank(switchESignApp.value()) ? switchESignApp.value() : switchESignApp.name();

        if (StrUtil.isNotBlank(appName)) {
            return appName;
        }

        Class<? extends ESignAppNameAdapter> adapterClass = switchESignApp.adapter();
        Map<String, ? extends ESignAppNameAdapter> beans = context.getBeansOfType(adapterClass);

        if (beans.isEmpty()) {
            // fallback 到默认 adapter
            ESignAppNameAdapter defaultAdapter = context.getBean(ESignAppNameAdapter.class);
            log.info("No custom ESignAppNameAdapter found, using default: {}", defaultAdapter.getClass().getSimpleName());
            return defaultAdapter.adaptation();
        }

        if (beans.size() > 1) {
            // 多个 adapter，选择 @Primary 或第一个，并打印 warn
            ESignAppNameAdapter chosen = beans.values().iterator().next();
            log.warn("Multiple ESignAppNameAdapter found: {}. Using {}",
                    beans.keySet(), chosen.getClass().getSimpleName());
            return chosen.adaptation();
        }

        // 正常情况：只有一个 bean
        return beans.values().iterator().next().adaptation();
    }
}
