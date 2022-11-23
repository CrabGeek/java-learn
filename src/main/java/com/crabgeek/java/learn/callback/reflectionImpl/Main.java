package com.crabgeek.java.learn.callback.reflectionImpl;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Request request = new Request();
        System.out.println("[Main]: 我开个线程去异步发请求");
        new Thread(() ->{
            try {
                request.send(Callback.class, Callback.class.getMethod("processResponse"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("[Main]: 请求发送完了，我去干点别的");
        Thread.sleep(10000);


    }
}
