package com.crabgeek.java.learn.thread.chapter.one;

import com.crabgeek.java.learn.thread.util.ThreadUtil;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class StatusDemo {
    public static final long MAX_TURN = 5L;
    static int threadSeqNumber = 0;
    static List<Thread> threadList = new ArrayList<>();

    private static void printThreadStatus() {
        for (Thread thread : threadList) {
            System.out.println(thread.getName() + " 状态为: " + thread.getState());
        }
    }

    private static void addStatusThread(Thread thread) {
        threadList.add(thread);
    }

    static class StatusDemoThread extends Thread {

        public StatusDemoThread() {
            super("statusPrintThread" + (++threadSeqNumber));
            addStatusThread(this);
        }

        @Override
        public void run() {
            System.out.println(getName() + ", 状态为: " + getState());

            for (int turn = 0; turn < MAX_TURN; turn++) {
                ThreadUtil.sleepMilliSeconds(500);
                printThreadStatus();
            }

            System.out.println(getName() + " - 运行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        addStatusThread(Thread.currentThread());

        StatusDemoThread sThread1 = new StatusDemoThread();
        System.out.println(sThread1.getName() + " - 状态为: " + sThread1.getState());

        StatusDemoThread sThread2 = new StatusDemoThread();
        System.out.println(sThread2.getName() + " - 状态为: " + sThread2.getState());

        StatusDemoThread sThread3 = new StatusDemoThread();
        System.out.println(sThread3.getName() + " - 状态为: " + sThread3.getState());

        sThread1.start();

        ThreadUtil.sleepMilliSeconds(500);

        sThread2.start();

        ThreadUtil.sleepMilliSeconds(500);

        sThread3.start();

        ThreadUtil.sleepMilliSeconds(500);

        Thread.sleep(100 * 1000L);
    }
}
