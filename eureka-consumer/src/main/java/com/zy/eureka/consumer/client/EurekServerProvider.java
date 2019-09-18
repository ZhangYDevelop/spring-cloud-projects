package com.zy.eureka.consumer.client;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @AUTHOR zhangy
 * 2019-09-18  20:57
 */
@FeignClient(value = "${provider.name}")
public interface EurekServerProvider {

    @GetMapping(value = "/api/{name}")
    String getName(@PathVariable String name );
}
