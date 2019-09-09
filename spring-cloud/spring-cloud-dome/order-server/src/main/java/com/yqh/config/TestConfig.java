package com.yqh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author yangq
 * Create time in 2019-09-09 11:05
 */
@Configuration
@ConfigurationProperties(prefix = "from.config")
@Data
public class TestConfig {

    private List<String> list;
}
