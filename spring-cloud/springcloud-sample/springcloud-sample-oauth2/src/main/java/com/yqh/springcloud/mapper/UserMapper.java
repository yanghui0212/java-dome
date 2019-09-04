package com.yqh.springcloud.mapper;

import com.yqh.springcloud.common.config.BaseMapper;
import com.yqh.springcloud.model.UserModel;

/**
 * @author yangq
 * Create time in 2018-08-01 12:48
 */
public interface UserMapper extends BaseMapper<UserModel> {
    UserModel selectByUserName(String toLowerCase);

    UserModel findUserAuthorityInfo(String toLowerCase);
}
