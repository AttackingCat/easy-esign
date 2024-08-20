package com.example.demo;

import io.github.easy.esign.adapter.ESignAppNameAdapter;
import org.springframework.stereotype.Component;

@Component
public class CustomerESignAdapterImpl implements ESignAppNameAdapter {

    @Override
    public String adaptation() {
        return "app123";
    }
}
