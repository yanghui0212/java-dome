package com.yqh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author yangq
 */
@SpringBootApplication
@EnableEurekaServer
@EnableWebSecurity
public class EurekaServer extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer.class, args);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        super.configure(http);
    }
}
