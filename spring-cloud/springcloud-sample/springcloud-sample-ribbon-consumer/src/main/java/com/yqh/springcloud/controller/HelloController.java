package com.yqh.springcloud.controller;

import com.yqh.springcloud.service.HelloService;
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
    private HelloService helloService;


    @CrossOrigin(origins = "*")
    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam("name") String name) {
        return helloService.sayHello(name);
    }


    @RequestMapping("/")
    public Object sayHello() {
        return helloService.clientInfo();
    }

}
