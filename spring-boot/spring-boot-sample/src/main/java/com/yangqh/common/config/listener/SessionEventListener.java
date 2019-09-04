package com.yangqh.common.config.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author yangq
 * Create time in 2018-07-13 15:16
 */
@Slf4j
@WebListener
public class SessionEventListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log.info("sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("sessionDestroyed");
    }
}
