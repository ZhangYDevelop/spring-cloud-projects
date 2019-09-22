package com.zy.eureka.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zy.eureka.consumer.client.EurekServerProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @AUTHOR zhangy
 * 2019-09-18  21:10
 */
@RestController
public class ClientTestController {

    private  final EurekServerProvider eurekServerProvider;

    public ClientTestController(EurekServerProvider eurekServerProvider) {
        this.eurekServerProvider = eurekServerProvider;
    }

    @HystrixCommand(defaultFallback = "fallBack")
    @GetMapping("/aip/{name}")
    public  String  getName(@PathVariable String name) throws InterruptedException {
        return  this.eurekServerProvider.getName(name);
    }

    public String fallBack() {
        return  "hystrix 超时 : 服务降价了";
    }
}
