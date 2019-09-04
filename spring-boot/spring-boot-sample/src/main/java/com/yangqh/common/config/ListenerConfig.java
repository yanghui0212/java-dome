package com.yangqh.common.config;

import com.yangqh.common.config.listener.RequestEventListener;
import com.yangqh.common.config.listener.SessionEventListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.EventListener;

/**
 * @author yangq
 * Create time in 2018-07-13 14:58
 */
@Configuration
@Order(1)
public class ListenerConfig {

/*    @Bean
    public ServletListenerRegistrationBean sessionEventListener(){
        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new SessionEventListener());
        return registrationBean;
    }*/

    @Bean
    public ServletListenerRegistrationBean requestEventListener(){
        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new RequestEventListener());
        return registrationBean;
    }
}
