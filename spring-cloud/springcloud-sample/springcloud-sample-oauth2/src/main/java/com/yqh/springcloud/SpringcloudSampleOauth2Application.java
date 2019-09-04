package com.yqh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.yqh.springcloud.mapper")
public class SpringcloudSampleOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudSampleOauth2Application.class, args);
    }
}
