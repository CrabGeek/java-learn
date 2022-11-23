package com.crabgeek.java.learn.callback.example;

public class XiaoMing implements CallBack{
    private XiaoHong xiaoHong;

    public XiaoMing() {
    }

    public XiaoMing(XiaoHong xiaoHong) {
        this.xiaoHong = xiaoHong;
    }

    public void askQuestion(final String question) throws InterruptedException {
        new Thread(() -> {
            try {
                xiaoHong.execute(XiaoMing.this, question);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(3000);
        work();
    }

    private void work() {
        System.out.println("小明问完就挂掉电话去工作了");
    }

    @Override
    public void resolve(String result) {
        System.out.printf("小红说:[%s]\n", result);
    }
}
