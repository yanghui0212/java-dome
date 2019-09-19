package com.yqh.enums;

import lombok.Getter;

/**
 * @author yangq
 * Create time in 2019-09-19 11:04
 */
@Getter
public enum ResultErrorEnum {

    SERVER_HYSTRIX_ENUM(503, "暂时无法提供服务！"),
    ;

    private Integer code;
    private String message;

    ResultErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
