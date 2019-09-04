package com.yqh.springcloud.controller;

import com.yqh.springcloud.service.feign.HelloFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangq
 * Create time in 2018-07-25 10:13
 */
@RestController
public class HelloController {

    @Autowired
    private HelloFeignService helloFeignService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam("name") String name) {
        return helloFeignService.sayHello(name);
    }

    @RequestMapping("/producer/discoveryClient")
    public Object clientInfo() {
        return helloFeignService.clientInfo();
    }
}
