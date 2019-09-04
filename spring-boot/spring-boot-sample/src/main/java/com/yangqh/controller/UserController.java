package com.yangqh.controller;

import com.yangqh.common.dto.BaseResponseDto;
import com.yangqh.model.UserModel;
import com.yangqh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author yangq
 * Create time in 2018-07-18 14:04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public BaseResponseDto<List<UserModel>> findAll() {
        BaseResponseDto<List<UserModel>> responseDto = new BaseResponseDto<>();
        responseDto.setObj(userService.findAll());
        return responseDto;
    }

    @RequestMapping("/find/{id}")
    public BaseResponseDto<UserModel> findById(@Min(2) @PathVariable("id") long id) {
        return new BaseResponseDto<>(userService.findById(id));
    }
}
