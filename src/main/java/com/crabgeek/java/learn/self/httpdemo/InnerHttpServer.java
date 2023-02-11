package com.crabgeek.java.learn.self.httpdemo;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @program: java-learn
 * @description:
 * @author: Guipeng.Xie
 * @create: 2023-02-11 21:01
 */
public class InnerHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8383), 0);

        httpServer.createContext("/", httpExchange -> {
            System.out.println("uri: " + httpExchange.getRequestURI().getQuery());
            httpExchange.sendResponseHeaders(200, "hello".length());
            httpExchange.getResponseBody().write("Hello".getBytes(StandardCharsets.UTF_8));
        });

        httpServer.createContext("/hello", httpExChange -> {
            System.out.println("-------uri: " + httpExChange.getRequestURI().getQuery());
            httpExChange.sendResponseHeaders(200, "hello-world".length());
            httpExChange.getResponseBody().write("hello-world".getBytes(StandardCharsets.UTF_8));
        });

        httpServer.start();
    }
}
