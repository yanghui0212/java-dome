package com.yangqh.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yangq
 * Create time in 2018-07-20 09:24
 */
@Component
@Slf4j
public class MyJob {

    public void task1() {
        log.info("task1 ----> begin");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("task1 ----> end");
    }

    public void task2() {
        log.info("task2 ----> begin");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("task2 ----> end");
    }
}
