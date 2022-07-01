package com.example.demo.spring.aopstudy;


import com.example.demo.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotVeryUsefulAspect {

    @Pointcut("execution(* transfer(..))") // the pointcut expression
    private void anyOldTransfer() {} // the pointcut signature

    @Pointcut("execution(public * *(..))")
    private void anyPublicOperation() {}

    @Pointcut("within(com.xyz.myapp.trading..*)")
    private void inTrading() {}

    @Pointcut("anyPublicOperation() && inTrading() && anyOldTransfer()")
    private void tradingOperation() {}

    @Pointcut("within(com.example.demo.domain.User)")
    private void indomain() {}

    @Pointcut("execution(public * *(..))")
    private void getMethod() {}

    @Pointcut("@annotation(MyAopAnno)")
    public void annocut() { }


    @Before("indomain() && anyPublicOperation()")
    public void doAccessCheck() {
        System.out.println("before Aop");
        // ...
    }

    @AfterReturning(pointcut = "indomain() && anyPublicOperation()", returning = "retVal")
    public void doAccessCheck2(String retVal) {
        System.out.println("AOp截获的返回值是: "+retVal);
        // ...
    }

    @AfterThrowing(pointcut = "indomain() && anyPublicOperation()", throwing = "ex")
    public void exceptionHandler(Exception ex) {
        System.out.println("AOp截获异常");
        // ...
    }

    @Around("indomain() && anyPublicOperation()")
    public void aroundHandler(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("参数" + arg);
        }
        Object obj = pjp.getThis();
        Object target = pjp.getTarget();
        Signature signature = pjp.getSignature();

        System.out.println(pjp.toString());
        System.out.println(" around before");
        Object retVal = pjp.proceed();
        System.out.println("round after");
        // ...
    }

    @Before("indomain() && anyPublicOperation()&& args(param)")
    public void paramHandler(JoinPoint joinPoint, User param) throws Throwable {
        if (param != null) {
            System.out.println(param);
        }
        System.out.println(param);
        // ...
    }

    @Before(value = "annocut()&&args(param)",argNames = "joinPoint,param")
    public void annoHandler(JoinPoint joinPoint, User param) throws Throwable {
        System.out.println("annotation cut");
        if (param != null) {
            System.out.println(param);
        }
        System.out.println(param);
        // ...
    }
}
