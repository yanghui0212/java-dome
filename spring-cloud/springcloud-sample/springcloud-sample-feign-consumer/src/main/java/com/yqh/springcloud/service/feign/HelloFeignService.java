package com.yqh.springcloud.service.feign;

import com.yqh.springcloud.service.feign.fallback.impl.HelloFeignServiceHystrixImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yangq
 * Create time in 2018-07-26 20:34
 */
@Primary
@FeignClient(name = "SPRINGCLOUD-SAMPLE-PRODUCER",fallback = HelloFeignServiceHystrixImpl.class )
public interface HelloFeignService {

    @RequestMapping("/sayHello")
    String sayHello(@RequestParam("name") String name);

    @RequestMapping()
    Object clientInfo();

}
