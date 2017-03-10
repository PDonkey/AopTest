package com.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Administrator on 2017/3/10.
 * Aspect 类负责管理注解的处理和代码织入
 *
 */
@Aspect
public class TraceAspect {
    private static final String POINTCUT_METHOD =
            "execution(@org.android10.gintonic.annotation.DebugTrace * *(..))";//切入点的方法
    private static final String POINTCUT_CONSTRUCTOR=
            "execution(@org.android10.gintonic.annotation.DebugTrace *.new(..))";//切入点的构造函数
    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotationWithDebugTrace(){}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotationDebugTrace(){}

    @Around("methodAnnotationWithDebugTrace()||constructorAnnotationDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        String className=methodSignature.getDeclaringType().getSimpleName();
        String methodName=methodSignature.getName();

        final StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Object result=joinPoint.proceed();
        stopWatch.stop();
        DebugLog.log(className,buildLogMessage(methodName,stopWatch.getToatalTimeMillis()));
        return result;
    }

    /**
     * log message
     * @param methodName 方法名称。
     * @param methodDurntion 方法的持续时间,以毫秒为单位
     * @return log内容
     */
    private static String buildLogMessage(String methodName,long methodDurntion){
        StringBuilder message=new StringBuilder();
        message.append("Gintonic-->");
        message.append(methodName);
        message.append("-->");
        message.append("[");
        message.append(methodDurntion);
        message.append("ms");
        message.append("]");
        return message.toString();

    }
}
