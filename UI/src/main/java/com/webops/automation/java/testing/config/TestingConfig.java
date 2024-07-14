package com.webops.automation.java.testing.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestingConfig {

    @Bean
    public BrowserStackWebDriverHelper browserStackWebDriverHelper() {
        return new BrowserStackWebDriverHelper();
    }
}