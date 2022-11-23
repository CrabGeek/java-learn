package com.crabgeek.java.learn.thread.chapter.one;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CreateDemo5 {
    public static final int MAX_TURN = 5;
    public static final int COMPUTE_TIMES = 100000000;

    static class ReturnableTask implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " 线程运行开始.");
            Thread.sleep(1000);
            for (int i = 0; i < COMPUTE_TIMES; i++) {
                int j = i * 1000;
            }
            long used = System.currentTimeMillis() - startTime;
            System.out.println(Thread.currentThread().getName() + " 线程运行结束.");
            return used;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReturnableTask returnableTask = new ReturnableTask();
        FutureTask<Long> futureTask = new FutureTask<>(returnableTask);
        Thread thread = new Thread(futureTask, "returnableThread");
        thread.start();
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName() + " 让子弹飞一会");
        System.out.println(Thread.currentThread().getName() + " 做一点自己的事情");

        for (int i = 0; i < COMPUTE_TIMES / 2; i++) {
            int j = i * 10000;
        }
        System.out.println(Thread.currentThread().getName() + " 获取并发任务结果");
        try {
            System.out.println(thread.getName() + "线程占用时间: " + futureTask.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 运行结束.");
    }
}
