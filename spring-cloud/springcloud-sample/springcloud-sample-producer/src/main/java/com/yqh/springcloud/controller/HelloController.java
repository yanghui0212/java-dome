package com.yqh.springcloud.controller;

import com.yqh.springcloud.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangq
 * Create time in 2018-07-25 10:18
 */
@RestController
public class HelloController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private HelloService helloService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam("name") String name) {
        return helloService.sayHello(name + "   FROM: " + port);
    }

    @RequestMapping()
    public Object clientInfo() {
        return discoveryClient;
    }
}
