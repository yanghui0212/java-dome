package com.yqh.config.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangq
 * Create time in 2018-09-17 14:59
 */
@Configuration
@Slf4j
public class RabbitmqConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {


    public final static String AMQ_FANOUT_EXCHANGE = "amq.fanout";
    public final static String AMQ_DIRECT_EXCHANGE = "amq.direct";

    public final static String USER_DIRECT_QUEUE = "user.direct.queue";
    public final static String USER_DIRECT_ROUTING_KEY = "user.direct.routing.key";

    /**
     * 固定时间得死信队列
     * */
    public final static String FIXED_TTL_QUEUE = "fixed.ttl.queue";
    public final static String FIXED_TTL_ROUTING_KEY = "fixed.ttl.routing.key";
    /**
     * 自定义时间死信队列
     * */
    public final static String CUSTOMIZE_TTL_QUEUE = "customize.ttl.queue";
    public final static String CUSTOMIZE_TTL_ROUTING_KEY = "customize.ttl.routing.key";

    public final static String DLX_QUEUE = "dlx.queue";

    public final static String USER_FANOUT_QUEUE_1 = "user.fanout.queue1";
    public final static String USER_FANOUT_QUEUE_2 = "user.fanout.queue2";
    public final static String USER_FANOUT_ROUTING_KEY = "user.fanout.routing.key";

    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * 更换消息队列消息转换器
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MessagePropertiesConverter messagePropertiesConverter() {
        return new DefaultMessagePropertiesConverter();
    }


    @Bean(name = "rabbitTemplate")
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        rabbitTemplate.setMessagePropertiesConverter(messagePropertiesConverter());
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        return rabbitTemplate;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("msg confirm");
        log.info("correlationData: {} ,ack: {} ,cause: {} ", correlationData, ack, cause);
        if (ack) {
            // 如果confirm返回成功，则进行更新
            log.info("confirm成功");
        } else {
            // 失败则进行具体的后续操作：重试或者补偿等
            log.info("异常处理...");
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

    }

    public static class CustomizeMessagePostProcessor implements MessagePostProcessor {

        private final Long failureTime; // 毫秒

        public CustomizeMessagePostProcessor(Long failureTime) {
            this.failureTime = failureTime;
        }

        @Override
        public Message postProcessMessage(Message message) throws AmqpException {
            message.getMessageProperties().setExpiration(failureTime.toString()); // 设置per-message的失效时间
            return message;
        }
    }
}
