package com.yqh.controller;

import com.yqh.mode.UserModel;
import com.yqh.producer.RabbitmqProducer;
import com.yqh.util.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangq
 * Create time in 2018-09-17 15:42
 */
@RestController
public class UserController {

    @Autowired
    private RabbitmqProducer rabbitmqProducer;
    @Autowired
    private Snowflake snowflake;

    @RequestMapping(value = "/fanout", method = RequestMethod.GET)
    public String sendFanoutMsg() {
        UserModel user = new UserModel();

        user.setAge(12);
        user.setName("fanout");
        user.setMessageId(snowflake.nextId() + "");
        rabbitmqProducer.sendFanoutMsg(user);
        return "success";
    }

    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    public String sendDirectMsg() {
        UserModel user = new UserModel();
        user.setAge(11);
        user.setName("direct");
        user.setMessageId(snowflake.nextId() + "");
        rabbitmqProducer.sendDirectMsg(user);
        return "success";
    }

    @RequestMapping(value = "/f/ttl", method = RequestMethod.GET)
    public String sendFixedTtlMsg() {
        UserModel user = new UserModel();
        user.setAge(15);
        user.setName("FixedTtl");
        user.setMessageId(snowflake.nextId() + "");
        rabbitmqProducer.sendFixedTtlMsg(user);
        return "success";
    }

    @RequestMapping(value = "/c/ttl", method = RequestMethod.GET)
    public String sendCustomizeTtlMsg() {
        UserModel user = new UserModel();
        user.setAge(16);
        user.setName("CustomizeTtl");
        user.setMessageId(snowflake.nextId() + "");
        rabbitmqProducer.sendCustomizeTtlMsg(user);
        return "success";
    }

}
