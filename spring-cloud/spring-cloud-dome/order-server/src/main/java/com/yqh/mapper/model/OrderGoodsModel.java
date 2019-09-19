package com.yqh.mapper.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author yangq
 * Create time in 2019-09-19 16:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_order_goods")
public class OrderGoodsModel extends BaseModel {

    @Id
    private Long id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;
    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Long goodsId;
    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;
    /**
     * 商品图片地址
     */
    @Column(name = "goods_url")
    private String goodsUrl;
    /**
     * 商品单价
     */
    @Column(name = "goods_price")
    private BigDecimal goodsPrice;
    /**
     * 商品数量
     */
    @Column(name = "goods_qty")
    private Integer goodsQty;

}
