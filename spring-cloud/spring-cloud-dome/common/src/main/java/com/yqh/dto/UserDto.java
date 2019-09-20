package com.yqh.dto;

import com.yqh.dto.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author yangq
 * Create time in 2019-09-20 15:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {
    private Long id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;
    /**
     * 用户密码
     */
    @NotBlank(message = "用户密码不能为空")
    private String password;
    /**
     * 用户pin
     */
    private String pin;
}
