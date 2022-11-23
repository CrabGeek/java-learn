package com.crabgeek.java.learn.callback.directCallImpl;

public class Request {
    public void send(Callback callback) throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("[Request]: 收到响应");
        callback.processResponse();
    }
}
