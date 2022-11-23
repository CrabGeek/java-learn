package com.crabgeek.java.learn.callback.interfaceImpl;

public class CallBackImpl implements Callback{
    @Override
    public void processResponse() {
        System.out.println("[CallBack]: 处理响应");
    }
}
