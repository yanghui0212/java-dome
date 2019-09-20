package com.yqh.dto.base;

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
}
