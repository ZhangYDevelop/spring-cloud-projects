package com.zy.eureka.consumer.conf;

import com.zy.eureka.consumer.annotion.Limited;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.*;


/**
 * @Author zhangy
 * @Date 15:48 2019/9/24
 **/

@Aspect
@Configuration
public class AopConfiguration {

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    private Map<Method, Semaphore>  semaphoreCache =new ConcurrentHashMap<>();

    @Around("@annotation(com.zy.eureka.consumer.annotion.Limited)")
    public Object  aroucdTimeLimitedInvocation(ProceedingJoinPoint proceedingJoinPoint) throws InterruptedException {
        Object returnObject = null;
        Signature signature = proceedingJoinPoint.getSignature();
        if(signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature)signature;
            Method method = methodSignature.getMethod();
            Semaphore semaphore = getSemaphore(method);
            try{
                // 在方法调用钱执行Semaphore#acquire()
                semaphore.acquire();
                proceedingJoinPoint.proceed();
            } catch (Throwable e) {

            } finally {
                // 方法执行完后，调用Semaphore#release()
                semaphore.release();
            }
        }
        return  returnObject;
    }

    private Semaphore getSemaphore(Method method) {
        return semaphoreCache.computeIfAbsent(method, k -> {
            Limited limited = method.getAnnotation(Limited.class);
            int value = limited.value();
            return  new Semaphore(value);
        });
    }
}
