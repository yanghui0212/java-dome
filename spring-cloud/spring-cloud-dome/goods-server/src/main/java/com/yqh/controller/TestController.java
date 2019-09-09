package com.yqh.controller;

import com.yqh.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangq
 * Create time in 2019-08-30 14:00
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${from.config}")
    private String fromConfig;

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public List<String> test() {
        return testService.test();
    }

    @GetMapping("/test/config")
    public String testConfig() {
        return fromConfig;
    }
}
