package com.yqh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqh.mapper.model.OrderModel;

import java.util.List;

/**
 * @author yangq
 * Create time in 2019-09-19 18:06
 */
public interface OrderMapper extends BaseMapper<OrderModel> {

    /**
     * 查询所有订单
     *
     * @return
     */
    List<OrderModel> selectAll();
}
