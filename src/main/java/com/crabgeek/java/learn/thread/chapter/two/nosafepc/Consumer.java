package com.crabgeek.java.learn.thread.chapter.two.nosafepc;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: java-learn
 * @description:
 * @author: Guipeng.Xie
 * @create: 2023-03-13 21:17
 */
public class Consumer implements Runnable{
    public static final int CONSUMER_GAP = 100;
    static final AtomicInteger TURN = new AtomicInteger(0);
    static final AtomicInteger CONSUMER_NO = new AtomicInteger(1);

    String name;
    Callable action = null;
    int gap = CONSUMER_GAP;

    public Consumer(Callable action, int gap) {
        this.action = action;
        this.gap = gap;
        this.name = "消费者-" + CONSUMER_NO.incrementAndGet();
    }

    @Override
    public void run() {
        while (true) {
            TURN.incrementAndGet();
            try {
                Object out = action.call();
                if (out != null) {
                    System.out.println("第" + TURN.get() + "轮消费: " + out);
                }
                Thread.sleep(gap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
