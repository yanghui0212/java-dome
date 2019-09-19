package com.yqh.controller;

import com.yqh.dto.ResultDto;
import com.yqh.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangq
 * Create time in 2019-08-30 14:00
 */
@RestController
@RequestMapping("/ribbon")
public class TestRibbonController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public List<String> test() {
        return testService.test();
    }

    @GetMapping("/get")
    public ResultDto getResultDto() {
        return testService.getResultDto();
    }
}
