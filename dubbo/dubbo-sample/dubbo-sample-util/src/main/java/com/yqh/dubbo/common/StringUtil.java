package com.yqh.dubbo.common;

/**
 * @author yangq
 * Create time in 2018-07-23 17:24
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return (str == null || str.trim().equals(""));
    }
}
