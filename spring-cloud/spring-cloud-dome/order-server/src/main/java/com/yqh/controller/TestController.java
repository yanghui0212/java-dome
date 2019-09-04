package com.yqh.controller;

import com.google.common.collect.Lists;
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
public class TestController {

    @GetMapping("/test")
    public List<String> test() {
        return Lists.newArrayList("ddd", "sss", "xxx");
    }
}
