package com.yqh.fallback;

import com.yqh.feign.GoodsServerFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yangq
 * Create time in 2019-09-09 13:43
 */
@Component
@Slf4j
public class GoodsServerFallBack implements FallbackFactory<GoodsServerFeign> {

    @Override
    public GoodsServerFeign create(Throwable throwable) {
        return new GoodsServerFeign() {
            @Override
            public String feignGet() {
                return "GOODS-SERVER暂时无法提供服务";
            }

            @Override
            public String feignPost(String str) {
                return "GOODS-SERVER暂时无法提供服务";
            }
        };
    }
}
