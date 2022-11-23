package com.crabgeek.java.learn.thread.chapter.one;

public class CreateDemo4 {
    private static final int MAX_TURN = 5;
    static int threadNo = 1;

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < MAX_TURN; j++) {
                    System.out.println(Thread.currentThread().getName() + ", 轮次: " + j);
                }
                System.out.println(Thread.currentThread().getName() + " 运行结束");
            }, "RunnableThread " + threadNo++);
            thread.start();
        }
        System.out.println(Thread.currentThread().getName() + " 运行结束.");
    }
}
