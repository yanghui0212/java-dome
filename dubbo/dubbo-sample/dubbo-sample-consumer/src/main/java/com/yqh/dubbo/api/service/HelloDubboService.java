package com.yqh.dubbo.api.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yqh.dubbo.service.item.HelloService;
import org.springframework.stereotype.Component;

/**
 * @author yangq
 * Create time in 2018-07-23 15:34
 */
@Component
public class HelloDubboService {

    @Reference(version = "1.0.0")
    private HelloService helloService;

    public void sayHello() {
        System.out.println(helloService.sayHello("world"));
    }

}
