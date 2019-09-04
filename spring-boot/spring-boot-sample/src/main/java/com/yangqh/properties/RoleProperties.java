package com.yangqh.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author yangq
 * Create time in 2018/07/05 15:24
 */
@Component
@PropertySource("classpath:role.properties")
@ConfigurationProperties(prefix = "role")
@Data
public class RoleProperties {
    private int level;
    private String name;
}
