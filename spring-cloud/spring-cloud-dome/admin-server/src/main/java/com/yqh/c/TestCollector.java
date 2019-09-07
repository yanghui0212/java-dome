package com.yqh.c;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangq
 * Create time in 2019-09-07 13:15
 */
@RestController
@RefreshScope
public class TestCollector {
    @Value("${test.a}")
    private String a;

    @GetMapping("/a")
    public String a() {
        return a;
    }
}
