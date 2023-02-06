package com.crabgeek.java.learn.netty.nio.socket.demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Server {
    private Charset charset = Charset.forName("UTF-8");
    private ByteBuffer byteBuffer = ByteBuffer.allocate(102410);
    private Map<SelectableChannel, Client> clientMap = new HashMap<>();

    static class Client {
        public String fileName;
        public long fileLength;
        public Instant startTime;
        public InetSocketAddress remoteAddress;
        public FileChannel fileChannel;
    }

    public void startServer() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(8998));
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server is listening...");

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = server.accept();
                    if (Objects.isNull(socketChannel)) {
                        continue;
                    }
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    Client client = new Client();
                    client.remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
                    clientMap.put(socketChannel, client);
                    System.out.println(socketChannel.getRemoteAddress() + ": 连接成功");
                } else if (key.isReadable()) {

                }
            }
        }
    }

}
