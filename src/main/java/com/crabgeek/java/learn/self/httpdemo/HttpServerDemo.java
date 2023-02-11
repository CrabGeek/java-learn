package com.crabgeek.java.learn.self.httpdemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @program: java-learn
 * @description:
 * @author: Guipeng.Xie
 * @create: 2023-02-11 19:35
 */
public class HttpServerDemo {
    private ServerSocket serverSocket;
    private ExecutorService executor;

    public HttpServerDemo() throws IOException {
        serverSocket = new ServerSocket(8282);
        executor = new ThreadPoolExecutor(10, 10, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), r -> {
                    Thread thread = new Thread(r);
                    thread.setName("CrabGeek-http-" + thread.getId());
                    return thread;
                });
    }

    public void run() {
        Socket socket;
        while (true) {
            try {
                socket = serverSocket.accept();
                executor.execute(new SocketRunnable(socket));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class SocketRunnable implements Runnable {
        private Socket socket;

        public SocketRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (InputStream inputStream = socket.getInputStream()) {
                Reader reader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(reader);
                OutputStream out = socket.getOutputStream();
                System.out.println("address: " + socket.getLocalAddress() + ":" + socket.getLocalPort());
                String line;
                while ((line = br.readLine()) != null && line.trim().length() != 0) {
                    System.out.println("------" + line);
                }
                PrintWriter pw = new PrintWriter(out);
                pw.write("HTTP/1.1 200 \r\n");
                pw.write("Content-Type: text/html;charset=UTF-8\r\n");
                pw.write("\r\n");
                pw.write("hello-world\r\n");

                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HttpServerDemo httpServerDemo = new HttpServerDemo();
        httpServerDemo.run();
    }

}
