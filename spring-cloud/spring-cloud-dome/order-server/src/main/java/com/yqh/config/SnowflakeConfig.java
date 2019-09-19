package com.yqh.config;

import com.yqh.dto.SnowflakeConfigDto;
import com.yqh.util.Snowflake;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangq
 * Create time in 2019-09-19 18:27
 */
@Configuration
public class SnowflakeConfig {

    @Bean
    @ConfigurationProperties(prefix = "snowflake")
    public SnowflakeConfigDto snowflakeConfigDto() {
        return new SnowflakeConfigDto();
    }

    @Bean
    public Snowflake snowflake() {
        SnowflakeConfigDto configDto = snowflakeConfigDto();
        Snowflake snowflake = new Snowflake(configDto.getDataCenterId(), configDto.getWorkerId());
        return snowflake;
    }
}
