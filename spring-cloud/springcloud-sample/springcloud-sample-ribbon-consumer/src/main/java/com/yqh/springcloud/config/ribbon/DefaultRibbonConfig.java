package com.yqh.springcloud.config.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.context.annotation.Bean;

/**
 * @author yangq
 * Create time in 2018-07-26 14:31
 */
public class DefaultRibbonConfig {

    @Bean
    public IRule ribbonRule() {
        return new RetryRule();
    }
}
