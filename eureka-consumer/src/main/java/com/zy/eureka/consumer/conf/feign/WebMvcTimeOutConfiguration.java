package com.zy.eureka.consumer.conf.feign;

import com.zy.eureka.consumer.web.servlet.TimeOutAnnotionHanlderInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author zhangy
 * @Date 21:13 2019/9/23
 **/
public class WebMvcTimeOutConfiguration  implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeOutAnnotionHanlderInterceptor());
    }
}
