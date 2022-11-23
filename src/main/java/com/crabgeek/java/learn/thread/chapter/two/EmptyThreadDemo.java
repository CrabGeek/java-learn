package com.crabgeek.java.learn.thread.chapter.two;

public class EmptyThreadDemo {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("线程名称: " + thread.getName());
        System.out.println("线程ID: " + thread.getId());
        System.out.println("线程优先级: " + thread.getPriority());
        System.out.println(thread.getName() + " 运行结束");
        thread.start();
    }
}
