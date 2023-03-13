package com.crabgeek.java.learn.thread.chapter.two.nosafepc;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: java-learn
 * @description:
 * @author: Guipeng.Xie
 * @create: 2023-03-12 22:41
 */
public class NoSafeDataBuffer <T>{
    public static final int MAX_AMOUNT = 10;
    private List<T> dataList = new LinkedList<>();

    private AtomicInteger amount = new AtomicInteger(0);

    public void add(T element) throws Exception {
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

    public T fetch() throws Exception {
        if (amount.get() <= 0) {
            System.out.println("队列已经空了");
            return null;
        }
        T element = dataList.remove(0);
        System.out.println(element + "");
        amount.incrementAndGet();

        if (amount.get() != dataList.size()) {
            throw new Exception(amount + " != " + dataList.size());
        }
        return element;
    }
}
