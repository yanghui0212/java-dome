package com.yangqh;

import com.yangqh.common.config.RabbitMQConfig;
import com.yangqh.mapper.db.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangq
 * Create time in 2018-07-19 10:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class RabbitMQTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() {

//        rabbitTemplate.convertAndSend(RabbitMQConfig.BASE_QUEUE_NAME, userMapper.selectByPrimaryKey(1));
        rabbitTemplate.convertAndSend(RabbitMQConfig.CUSTOMIZE_TIME_DELAY_QUEUE, userMapper.selectByPrimaryKey(3), new RabbitMQConfig.CustomizeMessagePostProcessor(5000L));
        rabbitTemplate.convertAndSend(RabbitMQConfig.FIXED_TIME_DELAY_QUEUE, userMapper.selectByPrimaryKey(4));
    }
}
