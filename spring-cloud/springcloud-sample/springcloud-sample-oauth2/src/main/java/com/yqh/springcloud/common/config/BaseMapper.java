package com.yqh.springcloud.common.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author yangq
 * Create time in 2018-08-01 12:42
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, tk.mybatis.mapper.common.BaseMapper<T> {
}
