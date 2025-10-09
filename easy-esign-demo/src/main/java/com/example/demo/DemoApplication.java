package com.example.demo;

import io.github.easy.esign.config.ESignConfig;
import io.github.easy.esign.core.ESignManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
//        new Demo01().test();
//        ESignManager.loadConfig(new ESignConfig());
    }
}
