package com.crabgeek.java.learn.thread.chapter.one;

import lombok.SneakyThrows;

public class DaemonDemo {
    public static final int SLEEP_GAP = 500;

    public static final int MAX_TURN = 4;

    static class DaemonThread extends Thread {
        public DaemonThread() {
            super("daemonThread");
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("--daemon线程开始.");
            for (int i = 0; ; i++) {
                System.out.println("--论次: " + i);
                System.out.println("--守护状态为: " + isDaemon());
                sleep(SLEEP_GAP);
            }
        }
    }

    public static void main(String[] args) {
        Thread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();

        Thread userThread = new Thread(() -> {
            System.out.println(">>用户线程开始");

            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(">>用户线程轮次: " + i);
                System.out.println(">>用户线程守护状态为: " + Thread.currentThread().isDaemon());
                try {
                    Thread.sleep(SLEEP_GAP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(">>用户进程结束");
            }
        });

        userThread.start();

        System.out.println("主线程守护状态为: " + Thread.currentThread().isDaemon());
        System.out.println("主线程运行结束");
    }
}
