package com.crabgeek.java.learn.thread.chapter.one;

import lombok.SneakyThrows;
import org.junit.Test;

public class InterruptDemo {
    public static final int SLEEP_GAP = 5000;
    public static final int MAX_TURN = 50;

    static int threadSeqNumber = 1;

    static class SleepThread extends Thread {
        static int threadSeqNumber = 1;

        public SleepThread() {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
        }

        @Override
        public void run() {
            try {
                System.out.println(getName() + " 进入睡眠.");
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(getName() + " 发生被异常打断.");
                return;
            }
            System.out.println(getName() + " 运行结束.");
        }

        public static void main(String[] args) throws InterruptedException {
            Thread thread1 = new SleepThread();
            thread1.start();

            Thread thread2 = new SleepThread();
            thread2.start();
            // 主线程休眠2秒
            Thread.sleep(2 * 1000);
            // 打断线程1
            thread1.interrupt();
            // 主线程休眠5秒
            Thread.sleep(5 * 1000);

            // 打断线程2，此时线程2已经终止
            thread2.interrupt();

            // 主线程休眠1秒
            Thread.sleep(1000);
            System.out.println("程序运行结束");
        }
    }

    @SneakyThrows
    @Test
    public void testInterrupt2() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("线程启动了");
                while (true) {
                    System.out.println(isInterrupted());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(isInterrupted()) {
                        System.out.println("线程结束了");
                        return;
                    }
                }
            }
        };

        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
