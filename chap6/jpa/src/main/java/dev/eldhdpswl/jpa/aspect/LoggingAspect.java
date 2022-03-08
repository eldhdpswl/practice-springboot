package dev.eldhdpswl.jpa.aspect;

import javassist.bytecode.SignatureAttribute;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around(value = "@annotation(LogExecutionTime)")
    /*
    *  joinpoint 기준으로 그 주위에서 작동을 하는 함수
    * Aspectj 에서 요구하는 정규표현식으로 작성
    * @Annotation(이름) - 이름에 들어가는 것은 annotation이름과 동일해야 된다.
    *
    * LogExecutionTime Annotation 주변에서 일어나는 일이 아래 함수이다.
    * */
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        /*
        * Object를 반환하는 이유는 joinPoint.proceed() 결과를 @Around가 실행되기전, 실행되고, 실행되고 난 다음에
          접근해야되기 때문에 시작하고나서 원래 그 함수가 반환해야될 것을 반환해야된다.
        * */

        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();  //다음에 실행할 advice, 함수자체가 진행을 한다.
        long execTime = System.currentTimeMillis() - startTime;
        logger.trace("method executed in {}", execTime);
        return null;

    }

    @Before(value = "@annotation(LogArguments)")
    public void logoArguments(JoinPoint joinPoint){
        /*
        * @Before 는 어떤 함수 시점 이전에 무슨일이 있었는지 확인만 하는것이기 떄문에 반환값은 없다. 이게 @Around와 차이점이다.
        * */
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();  //형변환 중요
        logger.trace("method description: [{}]", methodSignature.getMethod());
        logger.trace("method name: [{}]", methodSignature.getName());
        logger.trace("declaring class: [{}]", methodSignature.getDeclaringType());

        Object[] arguments = joinPoint.getArgs();
        if (arguments.length == 0){
            logger.trace("no arguments");
        }
        for (Object argument : arguments){
            logger.trace("argument : [{}]", argument);
        }

    }


    @AfterReturning(value = "@annotation(LogReturn)", returning = "returnValue") //반환값을 받아오기 위해 사용
    public void logResults(JoinPoint joinPoint, Object returnValue){
        /*
        * @AfterReturning 는 함수가 실행되고 반환값을 체크하기위한 것으로 Object returnValue을 통해서 반환값을 확인한다.
        * */
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.trace("method [{}]", methodSignature.getName());
        logger.trace("return type: [{}]", methodSignature.getReturnType());

        logger.trace("return value: [{}]", returnValue);
    }


}
