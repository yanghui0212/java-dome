package com.yqh.dto.base;

import com.yqh.enums.ResultErrorEnum;
import lombok.Data;

/**
 * @author yangq
 * Create time in 2019-09-11 17:51
 */
@Data
public class ResultDto<T> {

    private T data;

    private Integer code;

    private String message;

    private boolean success;

    public static ResultDto build(ResultErrorEnum resultEnum) {
        ResultDto result = new ResultDto();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setSuccess(false);
        return result;
    }

    public static ResultDto success(Object data) {
        ResultDto result = new ResultDto();
        result.setCode(200);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static ResultDto error(Exception e) {
        ResultDto result = new ResultDto();
        result.setCode(500);
        result.setSuccess(false);
        result.setData(e.getMessage());
        return result;
    }
}
