package com.crabgeek.java.learn.thread.chapter.one;

import ch.qos.logback.core.hook.ShutdownHook;
import com.crabgeek.java.learn.thread.chapter.one.threadpool.CreateTreadPoolDemo;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.concurrent.*;

/**
 * @program: java-learn
 * @description:
 * @author: Guipeng.Xie
 * @create: 2023-02-07 22:24
 */
public class IODenseTasksDemo {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private static final int IO_MAX = Math.max(2, CPU_COUNT * 2);

    private static final long KEEP_ALIVE_SECONDS = 30;

    private static final int QUEUE_SIZE = 128;

    private static class IODenseTargetThreadPoolLazyHolder {
        private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(IO_MAX, IO_MAX, KEEP_ALIVE_SECONDS,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(QUEUE_SIZE), new CreateTreadPoolDemo.SimpleThreadFactory());
        static {
            EXECUTOR.allowCoreThreadTimeOut(true);
            Runtime.getRuntime().addShutdownHook(new Thread(EXECUTOR::shutdown));
        }
    }
}
