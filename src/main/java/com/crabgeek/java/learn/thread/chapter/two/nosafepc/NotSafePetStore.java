package com.crabgeek.java.learn.thread.chapter.two.nosafepc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: java-learn
 * @description:
 * @author: Guipeng.Xie
 * @create: 2023-03-13 21:16
 */
public class NotSafePetStore {
    private static NoSafeDataBuffer<IGoods> noSafeDataBuffer = new NoSafeDataBuffer<>();

    static Callable<IGoods> produceAction = () -> {
        IGoods goods = Goods.produceOne();
        try {
            noSafeDataBuffer.add(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    };

    static Callable<IGoods> consumerAction = () -> {
        IGoods goods = null;
        try {
            goods = noSafeDataBuffer.fetch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    };

    public static void main(String[] args) {
        System.setErr(System.out);
        final int THREAD_TOTAL = 20;
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_TOTAL);
        for (int i = 0; i < 5; i++) {
            pool.submit(new Producer(produceAction, 500));
            pool.submit(new Consumer(consumerAction, 1500));
        }
    }
}
