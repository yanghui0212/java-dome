package com.yqh.controller;

import com.yqh.dto.UserDto;
import com.yqh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author yangq
 * Create time in 2019-09-20 16:08
 */
@RestController
public class UserController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDto register(@RequestBody @Valid UserDto userDto) {
        return userService.register(userDto);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody @Valid UserDto userDto, HttpServletResponse response) {
        return userService.login(userDto, request, response);
    }

    @PostMapping("/logout")
    public void logout(UserDto userDto, HttpServletResponse response) {
        userService.logout(userDto, request, response);
    }
}
