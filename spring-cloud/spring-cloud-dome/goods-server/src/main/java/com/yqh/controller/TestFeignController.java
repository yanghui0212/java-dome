package com.yqh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangq
 * Create time in 2019-08-30 14:00
 */
@RestController
@RequestMapping("feign")
@Slf4j
public class TestFeignController {
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String feignGet() {
        return "get";
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String feignPost(@RequestBody String value) {
        log.info(value);
        return "POST";
    }
}
