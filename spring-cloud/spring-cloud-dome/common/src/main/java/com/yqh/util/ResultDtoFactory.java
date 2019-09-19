package com.yqh.util;

import com.yqh.dto.ResultDto;
import com.yqh.enums.ResultErrorEnum;

/**
 * @author yangq
 * Create time in 2019-09-19 10:29
 */
public class ResultDtoFactory {

    public static ResultDto build(ResultErrorEnum resultEnum) {
        ResultDto result = new ResultDto();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setSuccess(false);
        return result;
    }

    public static ResultDto build(Object data) {
        ResultDto result = new ResultDto();
        result.setCode(200);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }
}
