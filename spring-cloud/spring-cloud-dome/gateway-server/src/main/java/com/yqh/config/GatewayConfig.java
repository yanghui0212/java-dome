package com.yqh.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * @author yangq
 * Create time in 2019-09-09 17:59
 */
@Configuration
public class GatewayConfig {

    @Bean(name = "ipKeyResolver")
    @Primary
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

/*    @Bean(name = "pathResolver")
    public KeyResolver pathResolver() {
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }*/
}
