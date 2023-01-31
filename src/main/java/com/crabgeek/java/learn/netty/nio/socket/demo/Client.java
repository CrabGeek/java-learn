package com.crabgeek.java.learn.netty.nio.socket.demo;

import com.crabgeek.java.learn.netty.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.logging.Logger;

public class Client {
    private Charset charset = Charset.forName("UTF-8");
    private static Logger logger = Logger.getGlobal();

    public void sendFile() {
        String fileName = "source.txt";
        String srcPath = IOUtil.getResourcePath(fileName);
        File file = new File(srcPath);
        if (!file.exists()) {
            logger.info("File does not exist");
            return;
        }

        try {
            FileInputStream inputStream = new FileInputStream(file);
            FileChannel fileChannel = inputStream.getChannel();
            SocketChannel clientSocketChannel = SocketChannel.open();
            clientSocketChannel.socket().connect(new InetSocketAddress("127.0.0.1", 7777));
            clientSocketChannel.configureBlocking(false);
            while (!clientSocketChannel.finishConnect()) {
                //不断自旋，直到连上客户端
            }
            logger.info("Connection successfully");
            ByteBuffer encodeFileNameBuffer = charset.encode(srcPath);
            clientSocketChannel.write(encodeFileNameBuffer);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            byteBuffer.putLong(file.length());
            byteBuffer.flip();

            clientSocketChannel.write(byteBuffer);
            byteBuffer.clear();

            logger.info("Start transmit file");

            int length = 0;
            long progress = 0;

            while ((length = fileChannel.read(byteBuffer)) > 0) {
                byteBuffer.flip();
                clientSocketChannel.write(byteBuffer);
                byteBuffer.clear();
                progress += length;
                logger.info("| " + (100 * progress / file.length()) + "% |");
            }
            if (length == -1) {
                IOUtil.closeQuietly(fileChannel);
                clientSocketChannel.shutdownOutput();
                IOUtil.closeQuietly(clientSocketChannel);
            }
            logger.info("Transmit file successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.sendFile();
    }
}
