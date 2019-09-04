package com.yangqh.service;

import com.yangqh.mapper.db.UserMapper;
import com.yangqh.mapper.db1.LogMapper;
import com.yangqh.model.LogModel;
import com.yangqh.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangq
 * Create time in 2018-07-18 14:05
 */
@Service
@Slf4j
@Transactional(rollbackFor = {RuntimeException.class})
@CacheConfig(cacheNames = "user")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LogMapper logMapper;

    public List<UserModel> findAll(){
        List<LogModel> logModels = logMapper.selectAll();
        return userMapper.findAll();
    }

    @Cacheable(key = "#id")
    public UserModel findById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }
}
