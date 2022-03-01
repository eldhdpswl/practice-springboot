package dev.eldhdpswl.gradledemo;

import org.springframework.stereotype.Component;

@Component
public class TestImplementation implements TestInterface{
    @Override
    public void sayHello() {
        System.out.println("hello I`m a bean");
    }
}
