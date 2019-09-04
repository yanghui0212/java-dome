package com.yqh.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author yangq
 * Create time in 2018-08-01 18:13
 */

/**
 * 刷新toekn  http://localhost:2233/oauth/token?grant_type=refresh_token&scope=all&client_id=client_test&client_secret=123456&refresh_token=570b364e-a9a5-4634-b4ce-b440387b2f44
 * 获取token  http://localhost:2233/oauth/token?username=user&password=123456&grant_type=password&scope=all&client_id=client_test&client_secret=123456
 */

@RestController
public class UserController {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/logout")
    public String revokeToken(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)) {
            return "注销成功";
        } else {
            return "注销失败";
        }
    }

}
