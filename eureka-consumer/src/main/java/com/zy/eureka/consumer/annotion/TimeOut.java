package com.zy.eureka.consumer.annotion;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangy
 * @Date 21:07 2019/9/23
 * 自定义降级注解   类比于@HystrixCommand(defaultFallback = "fallBack")
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TimeOut {

    long value();

    TimeUnit timeUnit() default TimeUnit.MICROSECONDS;

    String fallBack() default "";
}
