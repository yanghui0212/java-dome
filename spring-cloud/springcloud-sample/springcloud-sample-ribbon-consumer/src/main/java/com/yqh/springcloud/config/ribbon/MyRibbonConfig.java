package com.yqh.springcloud.config.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

/**
 * @author yangq
 * Create time in 2018-07-26 15:52
 */
public class MyRibbonConfig {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
