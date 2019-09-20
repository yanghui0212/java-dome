package com.yqh.service;

import com.yqh.dto.OrderDto;
import com.yqh.enums.OrderStatusEnum;
import com.yqh.mapper.OrderMapper;
import com.yqh.mapper.model.OrderModel;
import com.yqh.util.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangq
 * Create time in 2019-09-20 09:51
 */
@Service
@Slf4j
public class OrderService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private Snowflake snowflake;
    @Autowired
    private OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public OrderModel save(OrderDto orderDto) {
        OrderModel model = new OrderModel();
        long orderId = snowflake.nextId();
        model.setId(orderId);
        model.setOrderStatus(OrderStatusEnum.WAIT_PAY.getCode());
        model.setOrderNumber(String.valueOf(orderId));
        model.setUserId(orderDto.getUserId());
        model.setRemark(orderDto.getRemark());
        orderMapper.insert(model);
        redisTemplate.opsForValue().set(String.valueOf(model.getId()), model);
        return model;
    }
}
