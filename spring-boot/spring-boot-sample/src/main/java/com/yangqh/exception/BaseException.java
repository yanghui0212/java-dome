package com.yangqh.exception;

/**
 * @author yangq
 * Create time in 2018/07/10 09:14
 */
public class BaseException extends RuntimeException {

    private String code;

    public BaseException() {
        super();
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
