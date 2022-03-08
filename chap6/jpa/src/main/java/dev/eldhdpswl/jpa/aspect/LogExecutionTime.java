package dev.eldhdpswl.jpa.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
//Target은 @interface가 어떠한 것에 붙을수 있는것인지 정의, ElementType.METHOD은 @LogExecutionTime를 메소드에만 붙일 수 있다.
@Retention(RetentionPolicy.RUNTIME)
// @Retention은 소스코드로 작성이된것이 실제로 어느시점까지 존재할 것인가에 대한 것이다.
public @interface LogExecutionTime {
}
