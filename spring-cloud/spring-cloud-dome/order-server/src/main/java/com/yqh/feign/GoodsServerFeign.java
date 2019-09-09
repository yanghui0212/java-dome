package com.yqh.feign;

import com.yqh.fallback.GoodsServerFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yangq
 * Create time in 2019-09-09 13:39
 */
@FeignClient(name = "goods-server", fallbackFactory = GoodsServerFallBack.class)
public interface GoodsServerFeign {

    @RequestMapping(value = "/feign/get", method = RequestMethod.GET)
    String feignGet();

    @RequestMapping(value = "/feign/post", method = RequestMethod.POST)
    String feignPost(@RequestBody String str);
}
