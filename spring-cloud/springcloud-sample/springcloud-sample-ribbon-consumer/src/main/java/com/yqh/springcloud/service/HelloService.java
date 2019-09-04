package com.yqh.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangq
 * Create time in 2018-07-25 10:13
 */
@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "sayHelloFallback")
    public String sayHello(String name) {
        return restTemplate.getForObject("http://springcloud-sample-producer/sayHello?name=" + name, String.class);
    }

    @HystrixCommand(fallbackMethod = "sayHelloFallback")
    public Object clientInfo() {
        return restTemplate.getForObject("http://springcloud-sample-producer/", Object.class);

    }

    public String sayHelloFallback(String name) {
        return "SPRINGCLOUD-SAMPLE-PRODUCER 无法访问";
    }

    public Object sayHelloFallback() {
        return "SPRINGCLOUD-SAMPLE-PRODUCER 无法访问";
    }
}
