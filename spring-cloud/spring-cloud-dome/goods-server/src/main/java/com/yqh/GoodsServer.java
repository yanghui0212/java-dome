package com.yqh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author yangq
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class GoodsServer {

    public static void main(String[] args) {
        SpringApplication.run(GoodsServer.class, args);
    }

}
