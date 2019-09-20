package com.yqh.dto;

import com.google.common.collect.Lists;
import com.yqh.dto.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author yangq
 * Create time in 2019-09-20 09:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDto extends BaseDto {

    private Long id;
    /**
     * 所属用户
     */
    private Long userId;
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 包含的商品信息
     */
    private List<GoodsDto> goods = Lists.newArrayList();
}
