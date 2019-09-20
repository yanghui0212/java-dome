package com.yqh.enums;

import lombok.Getter;

/**
 * @author yangq
 * Create time in 2019-09-20 15:32
 */
@Getter
public enum OrderStatusEnum {
    WAIT_PAY("WAIT_PAY", "待支付"),
    HAS_COMPLETE("HAS_COMPLETE", "完成"),
    ;

    private String code;
    private String message;

    OrderStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
