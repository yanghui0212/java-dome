package com.yqh.producer;

import com.yqh.mode.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.yqh.config.rabbitmq.RabbitmqConfig.*;

/**
 * @author yangq
 * Create time in 2018-09-17 15:15
 */
@Component
@Slf4j
public class RabbitmqProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDirectMsg(UserModel user) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(user.getMessageId());
        rabbitTemplate.convertAndSend(AMQ_DIRECT_EXCHANGE,
                USER_DIRECT_ROUTING_KEY,
                user,
                correlationData);

    }

    /**
     * 死信消息
     */
    public void sendCustomizeTtlMsg(UserModel user) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(user.getMessageId());
        rabbitTemplate.convertAndSend(AMQ_DIRECT_EXCHANGE,
                CUSTOMIZE_TTL_ROUTING_KEY,
                user,
                new CustomizeMessagePostProcessor(50000L),
                correlationData);

    }

    /**
     * 死信消息
     */
    public void sendFixedTtlMsg(UserModel user) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(user.getMessageId());
        rabbitTemplate.convertAndSend(AMQ_DIRECT_EXCHANGE,
                FIXED_TTL_ROUTING_KEY,
                user,
                correlationData);

    }

    public void sendFanoutMsg(UserModel user) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(user.getMessageId());
        rabbitTemplate.convertAndSend(AMQ_FANOUT_EXCHANGE,
                USER_FANOUT_ROUTING_KEY,
                user, correlationData);
    }

}
