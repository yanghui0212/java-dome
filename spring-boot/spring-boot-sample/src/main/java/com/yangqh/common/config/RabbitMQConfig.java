package com.yangqh.common.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangq
 * Create time in 2018-07-19 10:29
 */
@Configuration
public class RabbitMQConfig {
    public final static String BASE_EXCHANGE_NAME = "baseExchange";//基础 EXCHANGE(交换器) 名称
    public final static String BASE_QUEUE_NAME = "base.queue.name";//基础queue(队列)名称
    public final static String BASE_ROUTE_KEY_NAME = "base.queue.name";//基础route-key(路由键)名称


    /**
     * 更换消息队列消息转换器
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue baseQueue() {
        // 第一个参数是 QUEUE 的名字,第二个参数是消息是否需要持久化处理
        return new Queue(BASE_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange baseExchange() {
        return new DirectExchange(BASE_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingBaseQueueBaseExchange() {
        return BindingBuilder.bind(baseQueue()).to(baseExchange()).with(BASE_ROUTE_KEY_NAME);
    }

    /**
     * RabbitMQ 延迟
     * */
    /**
     * 发送到该队列的message会在一段时间后过期进入到delay_process_queue
     * 队列里所有的message都有统一的失效时间
     */
    public final static String FIXED_TIME_DELAY_QUEUE = "fixed.time.delay_queue";
    /**
     * 发送到该队列的message会在一段时间后过期进入到delay_process_queue
     * 每个message可以控制自己的失效时间
     */
    public final static String CUSTOMIZE_TIME_DELAY_QUEUE = "customize.time.delay.queue";
    /**
     * message失效后进入的队列，也就是实际的消费队列
     */
    public final static String DELAY_PROCESS_QUEUE = "delay.process.queue";
    /**
     * 交换器
     * DELAY_EXCHANGE 延时队列绑定的
     * DELAY_PROCESS_EXCHANGE 实际的消费队列绑定的
     */
    public final static String DELAY_EXCHANGE = "delay.exchange";
    public final static String DELAY_PROCESS_EXCHANGE = "delay.process.exchange";

    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE);
    }

    @Bean
    public DirectExchange delayProcessExchange() {
        return new DirectExchange(DELAY_PROCESS_EXCHANGE);
    }

    @Bean
    public Queue fixedTimeDelayQueue() {
        return QueueBuilder.durable(FIXED_TIME_DELAY_QUEUE)
                .withArgument("x-dead-letter-exchange", DELAY_PROCESS_EXCHANGE) // DLX
                .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE) // dead letter携带的routing key
                .withArgument("x-message-ttl", 20000) // 设置队列的过期时间
                .build();
    }

    @Bean
    public Queue customizeTimeDelayQueue() {
        return QueueBuilder.durable(CUSTOMIZE_TIME_DELAY_QUEUE)
                .withArgument("x-dead-letter-exchange", DELAY_PROCESS_EXCHANGE) // DLX
                .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE) // dead letter携带的routing key
                .build();
    }

    @Bean
    public Queue delayProcessQueue() {
        return new Queue(DELAY_PROCESS_QUEUE, true);
    }

    @Bean
    Binding fixedTimeDelayQueueDelayExchangeBinding() {
        return BindingBuilder.bind(fixedTimeDelayQueue()).to(delayExchange()).with(FIXED_TIME_DELAY_QUEUE);
    }

    @Bean
    Binding customizeTimeDelayQueueDelayExchangeBinding() {
        return BindingBuilder.bind(customizeTimeDelayQueue()).to(delayExchange()).with(CUSTOMIZE_TIME_DELAY_QUEUE);
    }

    @Bean
    Binding delayProcessQueueDelayProcessExchangeBinding() {
        return BindingBuilder.bind(delayProcessQueue()).to(delayProcessExchange()).with(DELAY_PROCESS_QUEUE);
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
