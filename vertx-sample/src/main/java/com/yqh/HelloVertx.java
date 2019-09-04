package com.yqh;

import io.vertx.core.Vertx;

/**
 * @author yangq
 * Create time in 2018-08-28 15:02
 */
public class HelloVertx {

    public static void main(String[] args) {
        Vertx.vertx().createHttpServer().requestHandler(request -> {
            request.response().putHeader("Content-Type", "text/html").
                    end("Hello World");
        }).listen(4040);
    }
}
