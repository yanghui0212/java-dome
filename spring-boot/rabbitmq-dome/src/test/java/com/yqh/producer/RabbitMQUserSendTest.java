package com.yqh.producer;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangq
 * Create time in 2018-09-17 15:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQUserSendTest {

    @Autowired
    private RabbitmqProducer rabbitmqProducer;
}