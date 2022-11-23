package com.crabgeek.java.learn.callback.example;

public class XiaoHong {
    public void execute(CallBack callBack, String question) throws InterruptedException {
        System.out.println(question);
        System.out.println("小红说:[啊啊啊啊啊，好刺激啊，我在吃辣条，一会打给你]");
        Thread.sleep(5000);
        String result = "下班我回家换套衣服，要不晚上10:30不见不散";
        callBack.resolve(result);
    }
}
