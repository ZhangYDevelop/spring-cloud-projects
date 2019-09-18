package com.zy.eureka.consumer.controller;

import com.zy.eureka.consumer.client.EurekServerProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/aip/{name}")
    public  String  getName(@PathVariable String name) {
        return  this.eurekServerProvider.getName(name);
    }
}
