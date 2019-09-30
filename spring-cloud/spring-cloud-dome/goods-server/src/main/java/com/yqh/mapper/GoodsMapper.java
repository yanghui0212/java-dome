package com.yqh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqh.mapper.model.GoodsModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yangq
 * Create time in 2019-09-19 16:31
 */
public interface GoodsMapper extends BaseMapper<GoodsModel> {

    /**
     * 查询所有商品
     *
     * @return
     */
    @Select("select * from t_goods")
    List<GoodsModel> selectAll();
}
