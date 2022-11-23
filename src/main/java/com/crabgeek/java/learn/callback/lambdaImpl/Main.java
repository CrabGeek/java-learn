package com.crabgeek.java.learn.callback.lambdaImpl;

import com.crabgeek.java.learn.callback.interfaceImpl.CallBackImpl;
import com.crabgeek.java.learn.callback.interfaceImpl.Callback;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Request request = new Request();
        System.out.println("[Main]: 我开个线程去异步发请求");
        new Thread(() -> {
            try {
                request.send(() -> {
                    System.out.println("[CallBack]: 处理响应");
                });
                request.send((s) -> {
                    System.out.println(s);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("[Main]:请求发完了，我去干点别的");
        Thread.sleep(10000);
    }
}
