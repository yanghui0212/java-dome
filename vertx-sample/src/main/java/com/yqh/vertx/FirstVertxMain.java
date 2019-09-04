package com.yqh.vertx;

import io.vertx.core.Vertx;

/**
 * @author yangq
 * Create time in 2018-08-28 16:23
 */
public class FirstVertxMain {

    public static void main(String[] args) {
         Vertx.vertx().deployVerticle(FirstVertx.class.getName());
    }
}
