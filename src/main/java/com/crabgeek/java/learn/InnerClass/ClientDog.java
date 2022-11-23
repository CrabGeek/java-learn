package com.crabgeek.java.learn.InnerClass;

public class ClientDog {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Dog.DogProxy dogProxy = dog.getDogProxy();
        dogProxy.doSomeThing("haha");

        dogProxy.doSomeThing("LALA");

        Dog dog1 = new Dog();
        Dog.DogProxy dogProxy1 = dog1.getDogProxy();
        dogProxy1.doSomeThing("KK");
    }
}
