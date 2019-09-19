package com.yqh.enums;

import lombok.Getter;

import static com.yqh.code.ServiceIdCode.GOODS_SERVER_SERVICE_ID;
import static com.yqh.code.ServiceIdCode.ORDER_SERVER_SERVICE_ID;

/**
 * @author yangq
 * Create time in 2019-09-19 11:10
 */
@Getter
public enum ServiceIdAndPathEnum {

    GOODS_SERVER_SERVICE_ID_and_path(GOODS_SERVER_SERVICE_ID, "http://GOODS-SERVER/"),
    ORDER_SERVER_SERVICE_ID_AND_PATH(ORDER_SERVER_SERVICE_ID, "http://ORDER-SERVER/"),
    ;

    private String serviceId;
    private String path;

    ServiceIdAndPathEnum(String serviceId, String path) {
        this.serviceId = serviceId;
        this.path = path;
    }

}
