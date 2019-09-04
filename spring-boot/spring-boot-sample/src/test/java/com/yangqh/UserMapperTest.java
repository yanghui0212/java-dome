package com.yangqh;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.yangqh.mapper.db.UserMapper;
import com.yangqh.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangq
 * Create time in 2018-07-18 13:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback(false)
    public void test() {
        List<UserModel> models1 = userMapper.selectAll();
        log.info(new Gson().toJson(models1));
        List<UserModel> models2 = userMapper.findAll();
        log.info(new Gson().toJson(models2));
        UserModel userModel = new UserModel();
        userModel.setEmail("yy@qq.com");
        userModel.setName("yy");
        int insertCount = userMapper.insert(userModel);
        log.info(insertCount + "");
    }

    @Test
    @Rollback(false)
    public void testPageHelper() {
        PageHelper.startPage(4, 4);
        List<UserModel> models = userMapper.selectAll();
        PageInfo<UserModel> pageInfo = new PageInfo<>(models);
        log.info(new Gson().toJson(pageInfo));
    }
}
