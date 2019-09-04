package com.yqh.dubbo.common.anotation;

import com.yqh.dubbo.common.ExcelCellType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author yangq
 * Create time in 2018-07-24 10:03
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD })
public @interface ExcelHeadMap {
    String name();
    ExcelCellType type() default ExcelCellType.defaultType;
}