package com.yangqh.common.config.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author yangq
 * Create time in 2018-07-13 15:12
 */
@Slf4j
public class RequestEventListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        log.info("requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        log.info("requestInitialized");
    }
}
