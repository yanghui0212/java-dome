package com.yqh.controller;

import com.yqh.config.TestConfig;
import com.yqh.dto.ResultDto;
import com.yqh.util.ResultDtoFactory;
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
        Thread.sleep(4000L);
        return testConfig.getList();
    }

    @GetMapping("/get")
    public ResultDto getResultDto() throws InterruptedException {
        Thread.sleep(4000L);
        return ResultDtoFactory.build(testConfig.getList());
    }
}
