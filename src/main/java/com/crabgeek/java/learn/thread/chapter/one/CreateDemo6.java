package com.crabgeek.java.learn.thread.chapter.one;

import lombok.SneakyThrows;

import java.util.concurrent.*;

public class CreateDemo6 {
    public static final int MAX_TURN = 5;
    public static final int COMPUTE_TIMES = 100000000;
    // 创建一个包含三个线程的线程池
    private static ExecutorService pool = Executors.newFixedThreadPool(3);

    static class DemoThread implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(Thread.currentThread().getName() + ", 轮次: " + i);
                Thread.sleep(10000);
            }
        }
    }

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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 执行线程实例无返回
        pool.execute(new DemoThread());

        // 执行线程实例无返回
        pool.execute(() -> {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(Thread.currentThread().getName() + ", 轮次: " + i);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Future<Long> future = pool.submit(new ReturnableTask());
        Long result = future.get();
        System.out.println("异步任务的执行结果为: " + result);
        Thread.sleep(Integer.MAX_VALUE * 1000L);
    }
}
