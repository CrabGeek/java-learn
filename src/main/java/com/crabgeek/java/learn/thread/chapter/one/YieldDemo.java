package com.crabgeek.java.learn.thread.chapter.one;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class YieldDemo {
    public static final int MAX_TURN = 100;
    public static AtomicInteger index = new AtomicInteger(0);

    private static Map<String, AtomicInteger> metric = new HashMap<>();

    private static void printMetric() {
        System.out.println("metric = " + metric);
    }

    static class YieldThread extends Thread {

        static int threadSeqNumber = 1;

        public YieldThread() {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
            metric.put(this.getName(), new AtomicInteger(0));
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println("线程优先级: " + getPriority());
                index.incrementAndGet();
                metric.get(this.getName()).incrementAndGet();

                if (i % 2 == 0) {
                    // 让步： 让出执行的权限
                    Thread.yield();
                }
            }
            printMetric();
            System.out.println(getName() + " 运行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        YieldThread yieldThread = new YieldThread();
        yieldThread.setPriority(Thread.MAX_PRIORITY);
        YieldThread yieldThread1 = new YieldThread();
        yieldThread1.setPriority(Thread.MIN_PRIORITY);
        System.out.println("启动线程");
        yieldThread.start();
        yieldThread1.start();
        Thread.sleep(10000);
    }
}
