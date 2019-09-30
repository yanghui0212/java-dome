package com.yqh.service;

import com.yqh.conversion.GoodsModelAndDtoConversion;
import com.yqh.dto.GoodsDto;
import com.yqh.dto.base.ResultDto;
import com.yqh.mapper.GoodsMapper;
import com.yqh.mapper.model.GoodsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangq
 * Create time in 2019-09-30 14:21
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Transactional(readOnly = true)
    public ResultDto<GoodsDto> findOne(long id) {
        GoodsModel goodsModel = goodsMapper.selectById(id);
        GoodsDto goodsDto = GoodsModelAndDtoConversion.getConversion().toDto(goodsModel);
        return ResultDto.success(goodsDto);
    }

    @Transactional(readOnly = true)
    public ResultDto<List<GoodsDto>> findList() {
        List<GoodsModel> goodsModels = goodsMapper.selectAll();
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultDto<GoodsDto> updateGoods(GoodsDto dto) {
        GoodsModel goodsModel = goodsMapper.selectById(dto.getId());
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultDto<String> deleteGoods(long id) {
        goodsMapper.deleteById(id);

        return ResultDto.success("ok");
    }
}
