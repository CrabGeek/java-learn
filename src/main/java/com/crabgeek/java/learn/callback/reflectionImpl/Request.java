package com.crabgeek.java.learn.callback.reflectionImpl;

import java.lang.reflect.Method;

public class Request {
    public void send(Class clazz, Method method) throws Exception {
        // 模拟等待等待响应
        Thread.sleep(3000);
        System.out.println("[Request]: 收到响应");
        method.invoke(clazz.newInstance());
    }
}
