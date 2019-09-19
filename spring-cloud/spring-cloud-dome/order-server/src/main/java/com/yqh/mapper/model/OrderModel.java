package com.yqh.mapper.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yangq
 * Create time in 2019-09-19 16:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_order")
public class OrderModel extends BaseModel {

    @Id
    private Long id;

    /**
     * 所属用户
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * 订单号
     */
    @Column(name = "order_number")
    private String orderNumber;
    /**
     * 订单状态
     */
    @Column(name = "order_status")
    private String orderStatus;

}
