package com.yqh.controller;

import com.yqh.dto.ResultDto;
import com.yqh.util.ResultDtoFactory;
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
    public ResultDto feignGet() {
        return ResultDtoFactory.build("get");
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public ResultDto feignPost(@RequestBody String value) {
        log.info(value);
        return ResultDtoFactory.build("post");
    }
}
