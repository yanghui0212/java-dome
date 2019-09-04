package com.yqh.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class DubboSampleProductorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSampleProductorApplication.class, args);
    }
}
