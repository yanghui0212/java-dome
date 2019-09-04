package com.yqh.dubbo.dto;

import com.yqh.dubbo.common.anotation.ExcelHeadMap;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yangq
 * Create time in 2018-07-24 10:16
 */
@Data
public class SimpleExcelDto {

    @ExcelHeadMap(name = "姓名")
    private String name;
    @ExcelHeadMap(name = "工资")
    private BigDecimal val;

}
