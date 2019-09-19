package com.yqh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangq
 * Create time in 2019-08-30 14:00
 */
@RestController
@RefreshScope
@RequestMapping("/bus")
public class TestBusController {

    @Value("${from.config}")
    private String fromConfig;

    @GetMapping("/config")
    public String testConfig() throws InterruptedException {
        Thread.sleep(6000L);
        return fromConfig;
    }
}
