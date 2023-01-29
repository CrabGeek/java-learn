package com.crabgeek.java.learn.netty.channel;

import com.crabgeek.java.learn.netty.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;

public class FileNIOCopyDemo {
    public static Logger logger = Logger.getGlobal();

    public static void main(String[] args) {
        String sourcePath = "source.txt";
        String srcPath = IOUtil.getResourcePath(sourcePath);
        logger.info("srcPath= " + srcPath);

        String destPath = "target.txt";
        String destDecodePath = IOUtil.buildResourcePath(destPath);
        logger.info("destPath=" + destDecodePath);

        nioCopyFile(srcPath, destDecodePath);
    }

    public static void nioCopyFile(String srcPath, String destPath) {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);

        try {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }

            long startTime = System.currentTimeMillis();

            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel inChannel = null;
            FileChannel outChannel = null;

            try {
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                inChannel = fis.getChannel();
                outChannel = fos.getChannel();

                int length = -1;
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                // 从输入管道读到buffer
                while((length = inChannel.read(buffer)) != -1) {
                    // 第一次切换，翻转buffer，变成读模式
                    buffer.flip();

                    int outLength = 0;

                    while ((outLength = outChannel.write(buffer)) != 0) {
                        System.out.println("写入的字节数字: " + outLength);
                    }
                    // 第二次切换: 清除buf，变成写入模式
                    buffer.clear();
                }
                // 强制刷新到磁盘
                outChannel.force(true);
            } finally {
                IOUtil.closeQuietly(outChannel);
                IOUtil.closeQuietly(fos);
                IOUtil.closeQuietly(inChannel);
                IOUtil.closeQuietly(fis);
            }

            long endTime = System.currentTimeMillis();

            logger.info("base复制毫秒数: " +(endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
