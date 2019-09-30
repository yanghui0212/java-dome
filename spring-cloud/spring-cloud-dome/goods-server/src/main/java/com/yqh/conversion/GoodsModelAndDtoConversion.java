package com.yqh.conversion;

import com.yqh.dto.GoodsDto;
import com.yqh.mapper.model.GoodsModel;

/**
 * @author yangq
 * Create time in 2019-09-30 15:48
 */
public class GoodsModelAndDtoConversion implements BaseConversion<GoodsModel, GoodsDto> {

    private static class SingletonClassInstance {
        private static final GoodsModelAndDtoConversion conversion = new GoodsModelAndDtoConversion();
    }

    private GoodsModelAndDtoConversion() {
    }

    public static GoodsModelAndDtoConversion getConversion() {
        return SingletonClassInstance.conversion;
    }

    @Override
    public GoodsModel toModel(GoodsDto dto) {
        GoodsModel model = new GoodsModel();
        return model;
    }

    @Override
    public GoodsDto toDto(GoodsModel model) {
        GoodsDto dto = new GoodsDto();
        dto.setId(model.getId());
        dto.setUserId(model.getUserId());
        dto.setGoodsName(model.getGoodsName());
        dto.setGoodsUrl(model.getGoodsUrl());
        dto.setGoodsPrice(model.getGoodsPrice());
        dto.setGoodsTotalQty(model.getGoodsTotalQty());
        dto.setCreationTime(model.getCreationTime());
        dto.setLastUpdateTime(model.getLastUpdateTime());
        dto.setRemark(model.getRemark());
        return dto;
    }
}
