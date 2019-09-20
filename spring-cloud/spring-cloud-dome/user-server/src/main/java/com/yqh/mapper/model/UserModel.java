package com.yqh.mapper.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangq
 * Create time in 2019-09-20 15:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user")
public class UserModel extends BaseModel {

    @TableId
    private Long id;
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 用户密码
     */
    @TableField("user_password")
    private String password;
    /**
     * 用户pin
     */
    @TableField("pin")
    private String pin;


}
