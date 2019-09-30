package com.yqh;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author yangq
 * @SpringCloudApplication = @SpringBootApplication + @EnableDiscoveryClient + @EnableCircuitBreaker
 */
@SpringCloudApplication
@EnableCaching
public class GoodsServer {

    public static void main(String[] args) {
        SpringApplication.run(GoodsServer.class, args);
    }
}
