package com.yqh.service;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author yangq
 * Create time in 2019-08-30 14:00
 */
@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "testFallback")
    public List<String> test() {
        return restTemplate.getForObject("http://ORDER-SERVER/order/test", List.class);
    }

    public List<String> testFallback() {
        return Lists.newArrayList("order-server 无法访问");
    }

}
