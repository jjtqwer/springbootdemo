package com.example.springbootdemo;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * @author ex_jingjintao
 */
public class Test {
    public static void main(String[] args) {
        Undertow server=Undertow.builder().addHttpListener(8080,"localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
                        httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE,"text/plain");
                        httpServerExchange.getResponseSender().send("Hello World");
                    }
                }).build();
        server.start();
        /*ClassLoader loader = Test.class.getClassLoader();
        while (loader !=null){
            System.out.println(loader);
            loader=loader.getParent();
        }*/
        /*int i;
        System.out.println("i = " + i);*/
    }
}
