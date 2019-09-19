package com.yqh.enums;

import lombok.Getter;

/**
 * @author yangq
 * Create time in 2019-09-11 17:59
 */
@Getter
public enum ExceptionEnum {

    SERVER_400_EXCEPTION(400, "服务器异常！"),
    SERVER_500_EXCEPTION(500, "服务器开小差！"),
    ;

    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
