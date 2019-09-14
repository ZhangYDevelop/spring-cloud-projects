package com.zy.spring.cloud.erueka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @AUTHOR zhangy
 * 2019-09-14  16:04
 */
public class EurekaServerBootStrap {

    @EnableAutoConfiguration
    @EnableEurekaServer
    public  static  class  EurekaServerConfigration {

    }

    public static void main(String[] args) {

        SpringApplication.run(EurekaServerConfigration.class, args);

    }
}
