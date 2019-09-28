package com.zy.spring.cloud.client.web.rest;

import com.zy.spring.cloud.client.Pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @AUTHOR zhangy
 * 2019-09-28  23:11
 */
@RestController
public class RestProviderController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/services")
    public Set<String> getAllServices() {
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
        //Thread.sleep(sleepTime);
        System.out.printf("[当前线程 : %s] 当前方法执行(模型) 消耗 %d 毫秒\n", Thread.currentThread().getName(), sleepTime);
        return "hello : " + name;
    }

    @GetMapping(value = "/api/resttemplate")
    public  String getNameEx(@RequestParam String name ) throws InterruptedException {
        int sleepTime = new Random().nextInt(3000);
        //Thread.sleep(sleepTime);
        System.out.printf("[当前线程 : %s] 当前方法执行(模型) 消耗 %d 毫秒\n", Thread.currentThread().getName(), sleepTime);
        return "hello : " + name;
    }


    @PutMapping("/api/user/put")
    public User putUser(@RequestBody User user) {
        user.setName("修改数据");
        return  user;
    }

    @PostMapping("/api/user/post")
    public User postUser(@RequestBody User user) {
        user.setName("新增数据");
        return  user;
    }

}
