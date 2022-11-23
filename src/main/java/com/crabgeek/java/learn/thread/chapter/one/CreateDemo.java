package com.crabgeek.java.learn.thread.chapter.one;

public class CreateDemo {

    public static final int MAX_TURN = 5;
    static int threadNo = 1;

    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class DemoThread extends Thread {

        public DemoThread() {
            super("DemoThread-" + threadNo++);
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(Thread.currentThread().getName() + ", 轮次: " + i);
            }
            System.out.println(Thread.currentThread().getName() + " 运行结束.");
        }
    }

    public static void main(String[] args) {
        Thread thread = null;
        for (int i = 0; i < 2; i++) {
            thread = new DemoThread();
            thread.start();
        }
        System.out.println(getCurThreadName() + "运行结束");
    }
}
