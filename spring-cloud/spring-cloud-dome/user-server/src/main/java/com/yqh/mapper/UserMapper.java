package com.yqh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqh.mapper.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author yangq
 * Create time in 2019-09-20 15:53
 */
public interface UserMapper extends BaseMapper<UserModel> {

    /**
     * 通过用户名查找用户
     *
     * @param userName
     * @return
     */

    UserModel selectByUserName(@Param("userName") String userName);

}
