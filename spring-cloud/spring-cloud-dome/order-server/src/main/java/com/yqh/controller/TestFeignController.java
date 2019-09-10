package com.yqh.controller;

import com.yqh.feign.GoodsServerFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangq
 * Create time in 2019-08-30 14:00
 */
@RestController
@RequestMapping("feign")
@Slf4j
public class TestFeignController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private GoodsServerFeign goodsServerFeign;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String feignGet() {
        return goodsServerFeign.feignGet();
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String feignPost(@RequestBody String value) {
        return goodsServerFeign.feignPost(value);
    }
}
