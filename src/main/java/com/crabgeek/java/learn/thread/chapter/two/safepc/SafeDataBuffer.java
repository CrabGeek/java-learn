package com.crabgeek.java.learn.thread.chapter.two.safepc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: java-learn
 * @description:
 * @author: Guipeng.Xie
 * @create: 2023-03-15 21:48
 */
public class SafeDataBuffer <T>{
    public static final int MAX_AMOUNT = 10;
    private BlockingQueue<T> dataList = new LinkedBlockingQueue<>();

    private AtomicInteger amount = new AtomicInteger(0);

    public synchronized void add(T element) throws Exception {
        if (amount.get() > MAX_AMOUNT) {
            System.out.println("队列已经满了");
            return;
        }
        dataList.add(element);
        System.out.println(element + " add");
        amount.incrementAndGet();

        if (amount.get() != dataList.size()) {
            throw new Exception(amount + " != " + dataList.size());
        }
    }

    public synchronized T fetch() throws Exception {
        if (amount.get() <= 0) {
            System.out.println("队列已经空了");
            return null;
        }

        T element = dataList.poll();
        System.out.println(element + "");
        amount.decrementAndGet();

        if (amount.get() != dataList.size()) {
            throw new Exception(amount + " != " + dataList.size());
        }
        return element;
    }
}
