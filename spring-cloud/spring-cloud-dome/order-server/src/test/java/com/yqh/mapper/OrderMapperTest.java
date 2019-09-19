package com.yqh.mapper;

import com.yqh.OrderServerTests;
import com.yqh.mapper.model.OrderModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yangq
 * Create time in 2019-09-19 18:10
 */
@Component
public class OrderMapperTest extends OrderServerTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void test() {
        List<OrderModel> models = orderMapper.selectAll();
        System.out.println();
    }
}
