package com.yangqh.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yangq
 * Create time in 2018/07/05 15:23
 */
@Component
@ConfigurationProperties(prefix = "admin")
@Data
public class AdminProperties {
    private String name;
    private int age;
    private String sex;
}
