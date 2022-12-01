package com.crabgeek.java.learn.thread.chapter.one;

public class SleepDemo {
    public static final int SLEEP_GAP = 5000;

    public static final int MAX_TURN = 50;

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
        for (int i = 0; i < 5; i++) {
            SleepThread sleepThread = new SleepThread();
            sleepThread.start();
        }
        System.out.println(Thread.currentThread().getName() + " 运行结束.");
    }
}
