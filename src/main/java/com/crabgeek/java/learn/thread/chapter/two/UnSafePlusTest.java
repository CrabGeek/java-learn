package com.crabgeek.java.learn.thread.chapter.two;

import java.util.concurrent.CountDownLatch;

/**
 * @program: java-learn
 * @description:
 * @author: Guipeng.Xie
 * @create: 2023-02-13 20:53
 */
public class UnSafePlusTest {

    static final int MAX_THREAD = 10;
    static final int MAX_TURN = 1000;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(MAX_THREAD);
        UnSafePlus counter = new UnSafePlus();
        Runnable runnable = () -> {
            for (int i = 0; i < MAX_TURN; i++) {
                counter.selfPlus();
            }
            countDownLatch.countDown();
        };

        for (int i = 0; i < MAX_THREAD; i++) {
            new Thread(runnable).start();
        }
        countDownLatch.await();
        System.out.println("理论结果: " + MAX_THREAD * MAX_TURN);
        System.out.println("实际结果: " + counter.getAmount());
        System.out.println("差距是: " + (MAX_TURN * MAX_THREAD - counter.getAmount()));
    }


    static class UnSafePlus {
        private Integer amount = 0;

        public void selfPlus() {
            amount++;
        }

        public Integer getAmount() {
            return amount;
        }
    }
}
