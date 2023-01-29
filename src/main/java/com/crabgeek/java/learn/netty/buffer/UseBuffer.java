package com.crabgeek.java.learn.netty.buffer;

import java.nio.IntBuffer;
import java.util.logging.Logger;

public class UseBuffer {

    static Logger logger = Logger.getGlobal();

    static IntBuffer intBuffer = null;

    public static void allocateTest() {
        // 调用allocate方法，而不是使用new
        intBuffer = IntBuffer.allocate(20);
        logger.info("--------after allocate--------");
        logger.info("position=" + intBuffer.position());
        logger.info("limit=" + intBuffer.limit());
        logger.info("capacity=" + intBuffer.capacity());
    }

    public static void putTest() {
        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }

        logger.info("--------after put--------");
        logger.info("position=" + intBuffer.position());
        logger.info("limit=" + intBuffer.limit());
        logger.info("capacity=" + intBuffer.capacity());
    }

    public static void flipTest() {
        intBuffer.flip();
        logger.info("--------after flip--------");
        logger.info("position=" + intBuffer.position());
        logger.info("limit=" + intBuffer.limit());
        logger.info("capacity=" + intBuffer.capacity());
    }

    public static void getTest() {
        for (int i = 0; i < 2; i++) {
            int j = intBuffer.get();
            logger.info("j = " + j);
        }

        logger.info("--------after get 2 int--------");
        logger.info("position=" + intBuffer.position());
        logger.info("limit=" + intBuffer.limit());
        logger.info("capacity=" + intBuffer.capacity());
    }

    public static void rewindTest() {
        intBuffer.rewind();

        logger.info("--------after rewind--------");
        logger.info("position=" + intBuffer.position());
        logger.info("limit=" + intBuffer.limit());
        logger.info("capacity=" + intBuffer.capacity());
    }

    public static void reReadTest() {
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                intBuffer.mark();
            }
            int j = intBuffer.get();
            logger.info("j = " + j);
        }

        logger.info("--------after reRead--------");
        logger.info("position=" + intBuffer.position());
        logger.info("limit=" + intBuffer.limit());
        logger.info("capacity=" + intBuffer.capacity());
    }

    public static void afterReset() {
        logger.info("----------- After reset-------------");
        intBuffer.reset();
        logger.info("position=" + intBuffer.position());
        logger.info("limit=" + intBuffer.limit());
        logger.info("capacity=" + intBuffer.capacity());

        for (int i = 2; i < 5; i++) {
            int j = intBuffer.get();
            logger.info("j = " + j);
        }
    }

    public static void clearDemo() {
        logger.info("----------- after clear -----------");
        intBuffer.clear();
        logger.info("position=" + intBuffer.position());
        logger.info("limit=" + intBuffer.limit());
        logger.info("capacity=" + intBuffer.capacity());
    }

    public static void main(String[] args) {
        allocateTest();
        putTest();
        flipTest();
        getTest();
        rewindTest();
        reReadTest();
        afterReset();
        clearDemo();
    }
}
