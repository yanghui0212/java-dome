package com.yqh.service;

import com.yqh.dto.UserDto;
import com.yqh.enums.ExceptionEnum;
import com.yqh.exception.BaseException;
import com.yqh.mapper.UserMapper;
import com.yqh.mapper.model.UserModel;
import com.yqh.util.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Objects.nonNull;

/**
 * @author yangq
 * Create time in 2019-09-20 15:54
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Snowflake snowflake;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    public UserDto register(UserDto userDto) {
        UserModel userModel = userMapper.selectByUserName(userDto.getUserName());
        if (nonNull(userModel)) {
            throw new BaseException(ExceptionEnum.USER_PRESENCE_EXCEPTION);
        }
        UserModel user = new UserModel();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setId(snowflake.nextId());
        userMapper.insert(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public UserDto login(UserDto userDto, HttpServletRequest request, HttpServletResponse response) {
        UserModel userModel = userMapper.selectByUserName(userDto.getUserName());
        if (nonNull(userModel)) {
            if (userDto.getPassword().equals(userModel.getPassword())) {
                String redisKey = "user:login:" + userModel.getId();
                redisTemplate.opsForValue().set(redisKey, userModel);
                response.setHeader("user", redisKey);
                Cookie cookie = new Cookie("user", redisKey);
                cookie.setPath("/");
                response.addCookie(cookie);
                return userDto;
            } else {
                throw new BaseException(ExceptionEnum.PASSWORD_ERROR_EXCEPTION);
            }
        } else {
            throw new BaseException(ExceptionEnum.PASSWORD_ERROR_EXCEPTION);
        }
    }

    public void logout(UserDto userDto, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
    }
}
