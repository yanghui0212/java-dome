package com.yqh.config.util;

import com.yqh.util.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangq
 * Create time in 2019-08-28 14:49
 */
@Configuration
@Slf4j
public class SnowflakeConfig {

    @Value("${snowflake.worker.id:1}")
    private long workerId;

    @Value("${snowflake.data.center.id:2}")
    private long dataCenterId;

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(dataCenterId, workerId);
    }
}
