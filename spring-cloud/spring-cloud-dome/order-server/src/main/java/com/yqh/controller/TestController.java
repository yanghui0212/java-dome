package com.yqh.controller;

import com.google.common.collect.Lists;
import com.yqh.dto.base.ResultDto;
import com.yqh.util.ResultDtoFactory;
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

    @GetMapping("/test")
    public List<String> test() throws InterruptedException {
        Thread.sleep(4000L);
        return Lists.newArrayList("123", "222");
    }

    @GetMapping("/get")
    public ResultDto getResultDto() throws InterruptedException {
        Thread.sleep(4000L);
        return ResultDtoFactory.build(Lists.newArrayList("123", "222"));
    }
}
