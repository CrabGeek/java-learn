package com.crabgeek.java.learn.thread.chapter.one.threadlocal;

import lombok.Data;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: java-learn
 * @description:
 * @author: Guipeng.Xie
 * @create: 2023-02-11 16:58
 */
public class ThreadLocalTest {

    @Data
    static class Foo {
        static final AtomicInteger AMOUNT = new AtomicInteger(0);
        int index = 0;
        int bar = 0;

        public Foo() {
            index = AMOUNT.incrementAndGet();
        }

        @Override
        public String toString() {
            return index + " @Foo {bar=" + bar + "}";
        }

        private static final ThreadLocal<Foo> LOCAL_FOO = new ThreadLocal<>();

        public static void main(String[] args) {
            ExecutorService pool = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 5; i++) {
                pool.execute(() -> {
                    if (Objects.isNull(LOCAL_FOO.get())) {
                        LOCAL_FOO.set(new Foo());
                    }
                    System.out.println("初始的本地值: " + LOCAL_FOO.get());

                    for (int j = 0; j < 10; j++) {
                        Foo foo = LOCAL_FOO.get();
                        foo.setBar(foo.getBar() + 1);
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("累加10次之后的本地值: " + LOCAL_FOO.get());
                    LOCAL_FOO.remove();
                });
            }
        }
    }
}
