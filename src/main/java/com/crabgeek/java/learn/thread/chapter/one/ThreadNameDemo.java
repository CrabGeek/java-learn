package com.crabgeek.java.learn.thread.chapter.one;

import lombok.SneakyThrows;

public class ThreadNameDemo {
    static class RunTarget implements Runnable {
        private static final int MAX_TURN = 3;
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                Thread.sleep(500L);
                System.out.println(Thread.currentThread().getName() + "-线程执行轮次: " + i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 实例化Runnable异步执行目标类
        RunTarget runTarget = new RunTarget();

        // 系统自动设置线程名称
        new Thread(runTarget).start();

        // 系统自动设置线程名称
        new Thread(runTarget).start();

        // 系统自动设置线程名称
        new Thread(runTarget).start();

        // 手动设置线程名称
        new Thread(runTarget, "手动命名线程-A").start();

        // 手动设置线程名称
        new Thread(runTarget, "手动命名线程-B").start();

        Thread.sleep(5 * 1000L);
    }
}
