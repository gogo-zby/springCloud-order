package com.hmily.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties("test")
@RefreshScope
public class TestConfig {

    private String name;

    private Integer age;
}
