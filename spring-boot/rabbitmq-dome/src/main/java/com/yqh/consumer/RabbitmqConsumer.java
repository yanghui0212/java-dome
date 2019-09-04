package com.yqh.consumer;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.yqh.config.rabbitmq.RabbitmqConfig;
import com.yqh.mode.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yangq
 * Create time in 2018-09-17 14:48
 */
@Component
@Slf4j
public class RabbitmqConsumer {

    private final static Gson gson = new Gson();

    @RabbitHandler
    @RabbitListener(queues = {RabbitmqConfig.USER_DIRECT_QUEUE})
    public void onUserDirectListener(@Payload UserModel obj, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        log.info("获取的消息: {} ,headers : {}", gson.toJson(obj), gson.toJson(headers));
        // 手动签收消息
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

    @RabbitHandler
    @RabbitListener(queues = {RabbitmqConfig.DLX_QUEUE})
    public void onDlxListener(@Payload UserModel obj, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        log.info("获取的消息: {} ,headers : {}", gson.toJson(obj), gson.toJson(headers));
        // 手动签收消息
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

    @RabbitHandler
    @RabbitListener(queues = {RabbitmqConfig.USER_FANOUT_QUEUE_2, RabbitmqConfig.USER_FANOUT_QUEUE_1})
    public void onUserFanoutListener(@Payload UserModel obj, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        log.info("获取的消息: {} ,headers : {}", gson.toJson(obj), gson.toJson(headers));
        // 手动签收消息
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }
}
