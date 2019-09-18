package com.zy.eureka.consumer;

import com.zy.eureka.consumer.client.EurekServerProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @AUTHOR zhangy
 * 2019-09-18  20:54
 */
//@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
//@SpringBootApplication == @EnableAutoConfiguration + @ComponentScan
//@ComponentScan("com.zy.eureka.consumer.controller")
public class EurekaConsumerBootStrap {

    private  final EurekServerProvider eurekServerProvider;

    public EurekaConsumerBootStrap(EurekServerProvider eurekServerProvider) {
        this.eurekServerProvider = eurekServerProvider;
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerBootStrap.class, args);
    }


}
