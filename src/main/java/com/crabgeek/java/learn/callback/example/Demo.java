package com.crabgeek.java.learn.callback.example;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        XiaoHong xiaoHong = new XiaoHong();
        XiaoMing xiaoMing = new XiaoMing(xiaoHong);

        String question = "小明说: [亲爱的，周五有部电影要首映，我关注了好久，我们一起去看吧，从19：00~23:30都可以看,你啥时候下班呢？]";
        xiaoMing.askQuestion(question);
    }
}
