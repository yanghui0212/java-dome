package com.yqh.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangq
 */
@SpringBootApplication
@EnableDubbo
public class ProducerApp {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }
}
