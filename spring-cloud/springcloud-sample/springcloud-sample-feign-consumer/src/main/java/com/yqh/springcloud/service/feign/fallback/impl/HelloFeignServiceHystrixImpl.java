package com.yqh.springcloud.service.feign.fallback.impl;

import com.yqh.springcloud.service.feign.HelloFeignService;
import org.springframework.stereotype.Component;

/**
 * @author yangq
 * Create time in 2018-07-27 13:07
 */
@Component
public class HelloFeignServiceHystrixImpl implements HelloFeignService {
    @Override
    public String sayHello(String name) {
        return "SPRINGCLOUD-SAMPLE-PRODUCER 无法访问";
    }

    @Override
    public Object clientInfo() {
        return null;
    }
}
