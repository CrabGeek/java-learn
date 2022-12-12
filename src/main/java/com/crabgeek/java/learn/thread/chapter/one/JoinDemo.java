package com.crabgeek.java.learn.thread.chapter.one;

public class JoinDemo {
    public static final int SLEEP_GAP = 5000;
    public static final int MAX_TURN = 5;

    static class SleepThread extends Thread {
        static int threadSeqNumber = 1;

        public SleepThread() {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
        }

        public void run() {
            try {
                for (int i = 0; i < MAX_TURN; i++) {
                    System.out.println(getName() + ", 睡眠轮次: " + i);
                    Thread.sleep(SLEEP_GAP);
                }
            } catch (InterruptedException e) {
                System.out.println(getName() + " 发生异常被中断.");
            }
            System.out.println(getName() + " 运行结束.");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new SleepThread();
        System.out.println("启动 thread1");
        thread1.start();
        try {
            // 合并线程1， 不限时
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new SleepThread();
        System.out.println("启动 thread2");
        thread2.start();
        try {
            thread2.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程运行结束");
    }
}
