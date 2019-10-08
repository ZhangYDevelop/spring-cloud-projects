package com.zy.eureka.consumer;

import com.zy.eureka.consumer.client.EurekServerProvider;
import feign.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @AUTHOR zhangy
 * 2019-09-18  20:54
 */
//@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableCircuitBreaker
@EnableAspectJAutoProxy
//@EnableHystrixDashboard
@EnableSwagger2
@Aspect
//@SpringBootApplication == @EnableAutoConfiguration + @ComponentScan
//@ComponentScan("com.zy.eureka.consumer.controller")
public class EurekaConsumerBootStrap {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerBootStrap.class, args);
    }

}
