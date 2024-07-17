package io.github.easy.esign.core.config;

import lombok.Data;

import java.util.Map;

@Data
public class ESignConfigs {
    Map<String, ESignConfig> configs;
}
