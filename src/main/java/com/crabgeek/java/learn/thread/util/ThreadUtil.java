package com.crabgeek.java.learn.thread.util;

import java.util.concurrent.locks.LockSupport;

public class ThreadUtil {
    public static void sleepMilliSeconds(int millisecond) {
        LockSupport.parkNanos(millisecond * 1000L * 1000L);
    }
}
