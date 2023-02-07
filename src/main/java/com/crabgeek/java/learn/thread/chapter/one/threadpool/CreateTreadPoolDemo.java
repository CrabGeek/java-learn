package com.crabgeek.java.learn.thread.chapter.one.threadpool;

import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateTreadPoolDemo {
    public static final int SLEEP_GAP = 500;

    static class TargetTask implements Runnable {

        static AtomicInteger taskNo = new AtomicInteger(1);
        protected String taskName;

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

    static class TargetTaskWithError extends TargetTask {
        @Override
        public void run() {
            super.run();
            throw new RuntimeException("Error from " + this.taskName);
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

    @SneakyThrows
    @Test
    public void testSubmit() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<Integer> future = pool.submit(() -> RandomUtils.nextInt(200, 300));

        try {
            Integer result = future.get();
            System.out.println("异步执行的结果: " + result);
        } catch (ExecutionException e) {
            System.out.println("异步调用被中断");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("异步调用过程中，发生了异常");
            e.printStackTrace();
        }
        pool.shutdown();
    }

    @Test
    public void testSubmit2() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<?> future = pool.submit(new TargetTaskWithError());

        try {
            if (future.get() == null) {
                System.out.println("执行完成");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        pool.shutdown();
    }

    // 自定义个线程工厂
     static public class SimpleThreadFactory implements ThreadFactory {

        static AtomicInteger threadNo = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            String threadName = "SimpleThread-" + threadNo.get();
            System.out.println("创建一个线程, 名称为: " + threadName);
            threadNo.incrementAndGet();
            Thread thread = new Thread(r, threadName);
            thread.setDaemon(true);
            return thread;
        }
    }

    // 测试线程工厂
    @Test
    @SneakyThrows
    public void testThreadFactory() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2, new SimpleThreadFactory());
        for (int i = 0; i < 5; i++) {
            threadPool.submit(new TargetTask());
        }
        Thread.sleep(10000);
        System.out.println("关闭线程池");
        threadPool.shutdown();
    }

    // 测试ThreadPoolHook，线程池回调方法测试
    @Test
    @SneakyThrows
    public void testThreadPoolHook () {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2)) {
            private ThreadLocal<Long> startTime = new ThreadLocal<>();
            @Override
            protected void terminated() {
                System.out.println("线程池已终止");
            }

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(r + "前钩子被执行");
                startTime.set(System.currentTimeMillis());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                long time = System.currentTimeMillis() - startTime.get();
                System.out.println("后钩子被执行, 执行时间: " + time);
            }
        };

        for (int i = 0; i < 5; i++) {
            pool.execute(new TargetTask());
        }

        Thread.sleep(10000);
        System.out.println("关闭线程池");
        pool.shutdown();
    }

    public static class CustomIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(r + " rejected; " + "- getTaskCount: " + executor.getTaskCount());
        }
    }

    @Test
    @SneakyThrows
    public void testCustomIgnorePolicy() {
        int corePoolSize = 2;
        int maxPoolSize = 4;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        CustomIgnorePolicy customIgnorePolicy = new CustomIgnorePolicy();
        SimpleThreadFactory simpleThreadFactory = new SimpleThreadFactory();
        ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, 10, unit, workQueue, simpleThreadFactory, customIgnorePolicy);

        poolExecutor.prestartAllCoreThreads();

        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(new TargetTask());
        }
        Thread.sleep(10000);
        System.out.println("关闭线程池");
        poolExecutor.shutdown();
    }
}
