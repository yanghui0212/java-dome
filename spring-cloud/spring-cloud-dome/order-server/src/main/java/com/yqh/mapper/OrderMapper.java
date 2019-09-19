package com.yqh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqh.mapper.model.OrderModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yangq
 * Create time in 2019-09-19 18:06
 */
public interface OrderMapper extends BaseMapper<OrderModel> {

    @Select("select * from t_order")
    List<OrderModel> selectAll();
}
