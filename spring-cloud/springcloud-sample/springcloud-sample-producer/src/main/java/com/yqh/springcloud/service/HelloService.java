package com.yqh.springcloud.service;

import org.springframework.stereotype.Service;

/**
 * @author yangq
 * Create time in 2018-07-25 10:19
 */
@Service
public class HelloService {

    public String sayHello(String name) {
        return "Hello " + name;
    }
}
