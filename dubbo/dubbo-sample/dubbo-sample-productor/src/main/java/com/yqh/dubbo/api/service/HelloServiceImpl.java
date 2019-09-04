package com.yqh.dubbo.api.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yqh.dubbo.service.item.HelloService;

/**
 * @author yangq
 * Create time in 2018-07-23 14:40
 */
@Service(version = "1.0.0", token = "true", loadbalance = "random", interfaceClass = HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
