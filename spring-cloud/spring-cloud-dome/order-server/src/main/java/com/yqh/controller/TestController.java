package com.yqh.controller;

import com.yqh.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangq
 * Create time in 2019-08-30 13:47
 */
@RestController
@RequestMapping("/order")
@RefreshScope
public class TestController {

    @Autowired
    private TestConfig testConfig;

    @GetMapping("/test")
    public List<String> test() throws InterruptedException {
        Thread.sleep(7000L);
        return testConfig.getList();
    }
}
