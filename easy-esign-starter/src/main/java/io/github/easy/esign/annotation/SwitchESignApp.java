package io.github.easy.esign.annotation;

import io.github.easy.esign.adapter.ESignAppNameAdapter;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SwitchESignApp {
    @AliasFor("value")
    String name() default "";

    @AliasFor("name")
    String value() default "";

    Class<? extends ESignAppNameAdapter> adapter() default ESignAppNameAdapter.class;
}