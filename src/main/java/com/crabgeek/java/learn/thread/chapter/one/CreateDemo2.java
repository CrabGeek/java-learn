package com.crabgeek.java.learn.thread.chapter.one;

public class CreateDemo2 {
    public static final int MAX_TURN = 5;
    static int threadNo = 1;
    
    static class RunTarget implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(Thread.currentThread().getName() + ", 轮次: " + i);
            }
            System.out.println(Thread.currentThread().getName() + " 运行结束");
        }
    }

    public static void main(String[] args) {
        Thread thread = null;
        for (int i = 0; i < 2; i++) {
            RunTarget runTarget = new RunTarget();
            thread = new Thread(runTarget, "RunnableThread" + threadNo++);
            thread.start();
        }
    }
}
