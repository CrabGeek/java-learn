package com.crabgeek.java.learn.thread.chapter.one;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

public class SalesDemo {
    public static final int MAX_AMOUNT = 5;

    static class StoreGoods extends Thread {
        private int goodsAmount = MAX_AMOUNT;
        StoreGoods(String name) {
            super(name);
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i <= MAX_AMOUNT; i++) {
                if (this.goodsAmount > 0) {
                    System.out.println(getName() + " 卖出一件, 还剩: " + (goodsAmount--));
                    sleep(10000);
                }
            }
            System.out.println(getName() + " 运行结束");
        }
    }

    static class MallGoods implements Runnable {
        private AtomicInteger goodsAmount = new AtomicInteger(MAX_AMOUNT);

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i <= MAX_AMOUNT; i++) {
                if (goodsAmount.get() > 0) {
                    System.out.println(Thread.currentThread().getName() + " 卖出一件, 还剩: "
                            + (goodsAmount.getAndDecrement()));
                    Thread.sleep(10000);
                }
            }
            System.out.println(Thread.currentThread().getName() + " 运行结束.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("商店版本的销售");
        for (int i = 0; i < 2; i++) {
            Thread thread = new StoreGoods("店员-" + i);
            thread.start();
        }

        Thread.sleep(1000);
        System.out.println("商场版本的销售");
        MallGoods mallGoods = new MallGoods();
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(mallGoods, "商场销售员-" + i);
            thread.start();
        }
        System.out.println(Thread.currentThread().getName() + " 运行结束.");
    }
}
