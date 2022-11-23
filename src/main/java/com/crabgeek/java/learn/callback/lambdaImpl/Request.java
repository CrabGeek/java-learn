package com.crabgeek.java.learn.callback.lambdaImpl;

import com.crabgeek.java.learn.callback.interfaceImpl.Callback;

import java.util.function.Consumer;

public class Request {
    public void send(Callback callback) throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("[Request]: 收到响应");
        callback.processResponse();
    }

    public void send(Consumer<String> callback) {
        callback.accept("This is Consumer");
    }
}
