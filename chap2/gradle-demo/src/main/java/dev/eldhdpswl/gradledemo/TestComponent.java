package dev.eldhdpswl.gradledemo;

import org.springframework.stereotype.Component;

@Component //component가 붙는 순간 Spring IoC에서 관리를 해주는 객체가 된다.
public class TestComponent {

    private TestInterface testInterface;

    public TestComponent(
            TestInterface testInterface,
            int gradeBean
    ) {
        this.testInterface = testInterface;
        System.out.println(gradeBean);
    }

    public void sayHello(){
        this.testInterface.sayHello();
    }


}
