package com.crabgeek.java.learn.thread.chapter.two.nosafepc;

import java.util.Random;
import java.util.UUID;

/**
 * @program: java-learn
 * @description:
 * @author: Guipeng.Xie
 * @create: 2023-03-13 21:28
 */
public class Goods implements IGoods{

    private String name;
    private Integer price;

    public Goods(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public static IGoods produceOne() {
        String  goodsName = UUID.randomUUID().toString();
        Random random = new Random();
        Integer randomPrice = random.nextInt();
        return new Goods(goodsName, randomPrice);
    }
}
