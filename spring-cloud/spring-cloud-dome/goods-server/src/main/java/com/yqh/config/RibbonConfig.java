package com.yqh.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangq
 * Create time in 2019-09-09 09:33
 */
@Configuration
public class RibbonConfig {

    @Bean
    public IRule ribbonRule() {
        return new RetryRule();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}