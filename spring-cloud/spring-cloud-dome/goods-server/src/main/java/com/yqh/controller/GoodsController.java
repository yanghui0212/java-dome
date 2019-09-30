package com.yqh.controller;

import com.yqh.dto.GoodsDto;
import com.yqh.dto.base.ResultDto;
import com.yqh.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangq
 * Create time in 2019-09-30 15:31
 */
@RestController
@RequestMapping("/goods/api")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    public ResultDto<GoodsDto> findOne(long id) {
        return goodsService.findOne(id);
    }

    public ResultDto<List<GoodsDto>> findList() {
        return goodsService.findList();
    }

    public ResultDto<GoodsDto> updateGoods(GoodsDto dto) {
        return goodsService.updateGoods(dto);
    }

    public ResultDto<String> deleteGoods(long id) {
        return goodsService.deleteGoods(id);
    }
}
