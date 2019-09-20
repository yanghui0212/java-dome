package com.yqh.feign.fallback;

import com.yqh.dto.base.ResultDto;
import com.yqh.enums.ResultErrorEnum;
import com.yqh.feign.GoodsServerFeign;
import com.yqh.util.ResultDtoFactory;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yangq
 * Create time in 2019-09-19 10:10
 */
@Component
@Slf4j
public class GoodsServerFallBack implements FallbackFactory<GoodsServerFeign> {

    @Override
    public GoodsServerFeign create(Throwable throwable) {
        return new GoodsServerFeign() {
            @Override
            public ResultDto feignGet() {
                return ResultDtoFactory.build(ResultErrorEnum.SERVER_HYSTRIX_ENUM);
            }

            @Override
            public ResultDto feignPost(String str) {
                return ResultDtoFactory.build(ResultErrorEnum.SERVER_HYSTRIX_ENUM);
            }
        };
    }
}