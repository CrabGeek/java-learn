package com.crabgeek.java.learn.thread.chapter.one;

public class StackAreaDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("当前线程名称: " + Thread.currentThread().getName());
        System.out.println("当前线程ID: " + Thread.currentThread().getId());
        System.out.println("当前线程状态: " + Thread.currentThread().getState());
        System.out.println("当前线程优先级: " + Thread.currentThread().getPriority());

        int a = 1, b = 1;
        int c = a / b;
        anotherFun();
        Thread.sleep(10000000);
    }

    private static void anotherFun() {
        int a = 1, b = 1;
        int c = a / b;
        anotherFuc2();
    }

    private static void anotherFuc2() {
        int a = 1, b = 1;
        int c = a / b;
    }
}
