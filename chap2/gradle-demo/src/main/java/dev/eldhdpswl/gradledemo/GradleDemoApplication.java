package dev.eldhdpswl.gradledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GradleDemoApplication {

	public static void main(String[] args) {
		/*
		TestComponent testComponent = new TestComponent(
				new TestInterface() {
					@Override
					public void sayHello() {
						System.out.println("hello world! this is temp interface");
					}
				}
		);
		testComponent.sayHello();
//		SpringApplication.run(GradleDemoApplication.class, args);

		 */

		SpringApplication.run(GradleDemoApplication.class, args);
	}

}
