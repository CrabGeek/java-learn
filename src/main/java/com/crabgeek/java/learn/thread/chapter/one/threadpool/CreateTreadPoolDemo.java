package com.crabgeek.java.learn.thread.chapter.one.threadpool;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateTreadPoolDemo {
    public static final int SLEEP_GAP = 500;

    static class TargetTask implements Runnable {

        static AtomicInteger taskNo = new AtomicInteger(1);
        private String taskName;

        public TargetTask() {
            taskName = "task-" + taskNo.get();
            taskNo.incrementAndGet();
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("任务: " + taskName + " doing");
            Thread.sleep(SLEEP_GAP);
            System.out.println(taskName + ": 运行结束");
        }
    }

    @SneakyThrows
    @Test
    public void testSingleThreadExecutor() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
            pool.execute(new TargetTask());
        }
        Thread.sleep(10000);
        pool.shutdown();
    }

    @SneakyThrows
    @Test
    public void testFixedThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
            pool.execute(new TargetTask());
        }

        Thread.sleep(10000);
        pool.shutdown();
    }

    @SneakyThrows
    @Test
    public void testNewCacheThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
            pool.submit(new TargetTask());
        }
        Thread.sleep(10000);
        pool.shutdown();
    }

    @SneakyThrows
    @Test
    public void testNewScheduledThreadPool() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 2; i++) {
            // 0 表示首次执行任务的延迟时间，500表示每次执行任务的间隔时间
            pool.scheduleAtFixedRate(new TargetTask(), 0, 500, TimeUnit.MILLISECONDS);
        }
        Thread.sleep(10000);
        pool.shutdown();
    }

    @SneakyThrows
    @Test
    public void testThreadPoolExecutor() {
        BlockingDeque<Runnable> taskQueue = new LinkedBlockingDeque<>();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 3, 5, TimeUnit.SECONDS, taskQueue);

        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
        }
        Thread.sleep(10000);
        pool.shutdown();
    }
}
