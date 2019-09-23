package com.zy.spring.cloud.erueka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @AUTHOR zhangy
 * 2019-09-14  16:04
 */
@EnableAutoConfiguration
@EnableEurekaServer
public class EurekaServerBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerBootStrap.class, args);
    }
}
