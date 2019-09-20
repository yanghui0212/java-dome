package com.yqh.dto;

import com.yqh.dto.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author yangq
 * Create time in 2019-09-20 15:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsDto extends BaseDto {

    private Long id;
    /**
     * 所属用户
     */
    private Long userId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品图片地址
     */
    private String goodsUrl;
    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;
    /**
     * 商品总数量
     */
    private Integer goodsTotalQty;
}
