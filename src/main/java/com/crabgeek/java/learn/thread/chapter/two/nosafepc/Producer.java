package com.crabgeek.java.learn.thread.chapter.two.nosafepc;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @program: java-learn
 * @description:
 * @author: Guipeng.Xie
 * @create: 2023-03-13 20:08
 */
public class Producer implements Runnable{
    public static final int PRODUCE_GAP = 200;
    static final AtomicInteger TURN = new AtomicInteger(0);
    static final AtomicInteger PRODUCER_NO = new AtomicInteger(1);
    public String name = null;
    public Callable action = null;
    public int gap = PRODUCE_GAP;

    public Producer(Callable action, int gap) {
        this.action = action;
        this.gap = gap;
        this.name = "生产者-" + PRODUCER_NO.incrementAndGet();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object out = action.call();
                if (out != null) {
                    System.out.println("第" + TURN.get() + "轮生产: " + out);
                }
                Thread.sleep(gap);
                TURN.incrementAndGet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
