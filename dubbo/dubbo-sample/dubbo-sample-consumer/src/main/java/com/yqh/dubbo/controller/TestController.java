package com.yqh.dubbo.controller;

import com.yqh.dubbo.api.service.HelloDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangq
 * Create time in 2019-08-30 14:00
 */
@RestController
public class TestController {

    @Autowired
    private HelloDubboService dubboService;

    @GetMapping("/say")
    public String say() {
        dubboService.sayHello();
        return "say hello";
    }
}
