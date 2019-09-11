package com.yqh.exception;

import com.yqh.enums.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yangq
 * Create time in 2019-09-11 17:50
 */
@Setter
@Getter
public class BaseException extends RuntimeException {

    private Integer code;

    public BaseException(Integer code) {
        super();
        this.code = code;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }

}
