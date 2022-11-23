package com.crabgeek.java.learn.callback.directCallImpl;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Request request = new Request();
        System.out.println("[Main]: 我开个线程去异步发请求");
        Callback callback = new Callback();
        new Thread(() -> {
            try {
                request.send(callback);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("[Main]:请求发完了，我去干点别的");
        Thread.sleep(10000);
    }
}
