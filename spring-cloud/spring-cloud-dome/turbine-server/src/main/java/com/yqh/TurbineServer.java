package com.yqh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * EnableHystrixDashboard 监控面板
 * EnableTurbine  聚合监控
 *
 * @author yangq
 */
@SpringBootApplication
@EnableTurbine
@EnableDiscoveryClient
@EnableHystrixDashboard
public class TurbineServer {

    public static void main(String[] args) {
        SpringApplication.run(TurbineServer.class, args);
    }

}
