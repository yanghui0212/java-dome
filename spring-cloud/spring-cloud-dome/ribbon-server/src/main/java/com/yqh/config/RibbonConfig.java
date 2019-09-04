package com.yqh.config;

import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangq
 * Create time in 2019-08-30 14:19
 */
@Configuration
public class RibbonConfig {

    @Bean
    public IRule ribbonRule() {
        return new RetryRule();
    }

    @Bean
    public IClientConfig iClientConfig() {
        DefaultClientConfigImpl config = new DefaultClientConfigImpl();


        return config;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
