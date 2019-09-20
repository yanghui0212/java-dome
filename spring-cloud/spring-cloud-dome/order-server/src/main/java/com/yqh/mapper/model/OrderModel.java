package com.yqh.mapper.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangq
 * Create time in 2019-09-19 16:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_order")
public class OrderModel extends BaseModel {

    @TableId
    private Long id;

    /**
     * 所属用户
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 订单号
     */
    @TableField("order_number")
    private String orderNumber;
    /**
     * 订单状态
     */
    @TableField("order_status")
    private String orderStatus;

}
