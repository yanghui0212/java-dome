package com.yqh.service;

import com.yqh.OrderServerTests;
import com.yqh.dto.OrderDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangq
 * Create time in 2019-09-20 09:57
 */
@Component
@Transactional
@Rollback(false)
public class OrderServiceTest extends OrderServerTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void save() {
        OrderDto dto = new OrderDto();
        orderService.save(dto);
    }
}
