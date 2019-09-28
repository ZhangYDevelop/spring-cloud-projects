package com.zy.eureka.consumer.controller;

import com.zy.eureka.consumer.client.EurekServerProvider;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @AUTHOR zhangy
 * 2019-09-18  21:10
 */
@RestController
public class ClientTestController {

    private  final EurekServerProvider eurekServerProvider;

    private final Environment environment;

    private final RestTemplateBuilder builder;

    public ClientTestController(EurekServerProvider eurekServerProvider, Environment environment, RestTemplateBuilder builder) {
        this.eurekServerProvider = eurekServerProvider;
        this.environment = environment;
        this.builder = builder;
    }

   // @HystrixCommand(defaultFallback = "fallBack")
//    @GetMapping("/api/{name}")
//    public  String  getName(@PathVariable String name) throws InterruptedException {
//        return  this.eurekServerProvider.getName(name);
//    }

    public String fallBack() {
        return  "hystrix 超时 : 服务降级了";
    }

    /**
     * 获取服务端口
     * eureka 当配置文件配置服务端口为0（随机端口），通过@Value("${server.port}") 是获取不到端口的，需要
     * 通过一下方法
     * @return
     */
    @GetMapping("/api/getport")
    public String getServiceProt() {
        return environment.getProperty("local.server.port");
    }

}


