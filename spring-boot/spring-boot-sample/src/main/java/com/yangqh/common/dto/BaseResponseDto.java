package com.yangqh.common.dto;

import lombok.Data;

/**
 * @author yangq
 * Create time in 2018/07/10 09:38
 */
@Data
public class BaseResponseDto<T> {

    private Integer code = 200;

    private String message;

    private String path;

    private Boolean success = true;

    private T obj;

    public BaseResponseDto() {
    }

    public BaseResponseDto(T obj) {
        this.obj = obj;
    }

    public BaseResponseDto(Integer code, String message, String path, Boolean success, T obj) {
        this.code = code;
        this.message = message;
        this.path = path;
        this.success = success;
        this.obj = obj;
    }
}
