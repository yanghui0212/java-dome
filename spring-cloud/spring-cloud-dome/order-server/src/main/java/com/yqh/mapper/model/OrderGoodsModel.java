package com.yqh.mapper.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author yangq
 * Create time in 2019-09-19 16:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_order_goods")
public class OrderGoodsModel extends BaseModel {

    @TableId
    private Long id;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Long orderId;
    /**
     * 商品id
     */
    @TableField("goods_id")
    private Long goodsId;
    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     * 商品图片地址
     */
    @TableField("goods_url")
    private String goodsUrl;
    /**
     * 商品单价
     */
    @TableField("goods_price")
    private BigDecimal goodsPrice;
    /**
     * 商品数量
     */
    @TableField("goods_qty")
    private Integer goodsQty;

}
