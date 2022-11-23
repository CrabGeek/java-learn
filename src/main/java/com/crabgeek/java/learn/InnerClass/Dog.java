package com.crabgeek.java.learn.InnerClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Dog {
    private String name;
    private String color;
    @Getter
    private final DogProxy dogProxy = new DogProxy();

    public void doSomething(String someThing) {
        System.out.println(someThing);
    }

    public class DogProxy {

        public void doSomeThing(String something) {
            System.out.println("-----------");
            doSomething(something);
            System.out.println("-----------");
        }
    }
}
