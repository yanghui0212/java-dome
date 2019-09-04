package com.yangqh.common.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author yangq
 * Create time in 2018-07-18 12:44
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
