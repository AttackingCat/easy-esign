package io.github.easy.esign.annotation;

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
}