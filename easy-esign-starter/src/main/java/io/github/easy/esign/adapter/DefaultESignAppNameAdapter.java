package io.github.easy.esign.adapter;

import org.springframework.stereotype.Component;

/**
 * adapter demo
 */
@Component
public class DefaultESignAppNameAdapter implements ESignAppNameAdapter {
    @Override
    public String adaptation() {
        return "default";
    }
}