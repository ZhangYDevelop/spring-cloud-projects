package com.zy.eureka.consumer.annotion;

import java.lang.annotation.*;

/**
 * @Author zhangy
 * @Date 15:58 2019/9/24
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Limited {
    /**
     * 最大限制数量
     * @return
     */
    int value();
}
