package com.zy.spring.cloud.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @AUTHOR zhangy
 * 2019-09-14  17:03
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
public class ConfigClientBootStrap {

    @Autowired
    private DiscoveryClient discoveryClient;


    public static void main(String[] args) {
        SpringApplication.run(ConfigClientBootStrap.class, args);
    }

    @GetMapping(value = "/services")
    public Set<String>  getAllServices() {
        return  new LinkedHashSet<>(discoveryClient.getServices());
    }

    @GetMapping(value = "/services/{serviceName}")
    public List<ServiceInstance> getServiceInstances(@PathVariable String serviceName) {
        return discoveryClient.getInstances(serviceName);
    }


    @GetMapping(value = "/services/{serviceName}/{instanceId}")
    public ServiceInstance getServiceINstance(@PathVariable String serviceName, @PathVariable
                                              String instanceId) {
        return getServiceInstances(serviceName) .stream()
                                                .filter(serviceInstance -> instanceId.equals(serviceInstance.getInstanceId()))
                                                .findFirst()
                                                .orElseThrow(() ->new RuntimeException("获取数据异常"));
    }

    @GetMapping(value = "/api/{name}")
    public  String getName(@PathVariable String name ) throws InterruptedException {
        int sleepTime = new Random().nextInt(3000);
        Thread.sleep(sleepTime);
        System.out.println("sleepTime: " + sleepTime);
        return "hello : " + name;
    }
}
