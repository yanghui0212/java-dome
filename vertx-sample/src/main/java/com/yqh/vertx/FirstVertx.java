package com.yqh.vertx;

import io.vertx.core.AbstractVerticle;

/**
 * @author yangq
 * Create time in 2018-08-28 16:23
 */
public class FirstVertx extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello Vertx!");
        }).listen(4041);
    }
}
