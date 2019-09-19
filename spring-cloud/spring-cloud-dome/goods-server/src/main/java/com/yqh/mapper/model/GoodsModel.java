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
@Table(name = "t_goods")
public class GoodsModel extends BaseModel {

    @Id
    private Long id;

    /**
     * 所属用户
     */
    @Column(name = "user_id")
    private Long userId;
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
     * 商品总数量
     */
    @Column(name = "goods_total_qty")
    private Integer goodsTotalQty;
}
