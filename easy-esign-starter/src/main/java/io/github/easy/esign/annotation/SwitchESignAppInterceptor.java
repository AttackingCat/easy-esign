package io.github.easy.esign.annotation;

import io.github.easy.esign.adapter.ESignAppNameAdapter;
import io.github.easy.esign.ESignManager;
import io.github.easy.esign.utils.StrUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

public class SwitchESignAppInterceptor implements MethodInterceptor {
    private final ApplicationContext context;

    public SwitchESignAppInterceptor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object invoke(@NotNull MethodInvocation invocation) throws Throwable {
        SwitchESignApp switchESignApp = filterAnnotation(invocation);
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

    private String determineAppName(SwitchESignApp switchESignApp) {
        String appName = StrUtil.isNotBlank(switchESignApp.value()) ? switchESignApp.value() : switchESignApp.name();
        if (StrUtil.isNotBlank(appName)) {
            return appName;
        } else if (switchESignApp.adapter() != null) {
            Class<? extends ESignAppNameAdapter> adapter = switchESignApp.adapter();
            Map<String, ? extends ESignAppNameAdapter> beansOfType = context.getBeansOfType(adapter);
            if (beansOfType.size() > 1) {
                throw new RuntimeException("SwitchApp adapter more than one");
            } else if (beansOfType.isEmpty()) {
                throw new RuntimeException("SwitchApp adapter must not be null");
            } else {
                return beansOfType.values().iterator().next().adaptation();
            }
        } else {
            throw new RuntimeException("SwitchApp name must not be null");
        }
    }

    private SwitchESignApp filterAnnotation(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        SwitchESignApp switchESignApp = method.getAnnotation(SwitchESignApp.class);
        if (switchESignApp == null) {
            Class<?> aClass = Objects.requireNonNull(invocation.getThis()).getClass();
            switchESignApp = aClass.getAnnotation(SwitchESignApp.class);
        }
        return switchESignApp;
    }
}
