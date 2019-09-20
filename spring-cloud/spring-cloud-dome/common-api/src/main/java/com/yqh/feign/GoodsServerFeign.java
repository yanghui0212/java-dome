package com.yqh.feign;

import com.yqh.dto.base.ResultDto;
import com.yqh.feign.fallback.GoodsServerFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.yqh.code.ServiceIdCode.GOODS_SERVER_SERVICE_ID;

/**
 * @author yangq
 * Create time in 2019-09-19 10:09
 */
@FeignClient(name = GOODS_SERVER_SERVICE_ID, fallbackFactory = GoodsServerFallBack.class)
public interface GoodsServerFeign {

    /***
     * 测试feign get方法
     * @return
     */
    @RequestMapping(value = "/feign/get", method = RequestMethod.GET)
    ResultDto feignGet();

    /***
     * 测试feign post方法
     * @param str
     * @return
     */
    @RequestMapping(value = "/feign/post", method = RequestMethod.POST)
    ResultDto feignPost(@RequestBody String str);
}
