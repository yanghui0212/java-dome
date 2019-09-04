package com.yangqh.mapper.db;

import com.yangqh.common.config.BaseMapper;
import com.yangqh.model.UserModel;

import java.util.List;

/**
 * @author yangq
 * Create time in 2018-07-18 12:48
 */
public interface UserMapper extends BaseMapper<UserModel> {
    /**
     * 返回所有用户
     *
     * @param
     * @return
     */
    List<UserModel> findAll();
}
