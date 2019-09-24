package com.zy.eureka.consumer.client;


import com.zy.eureka.consumer.conf.feign.fallback.ServiceProviderFallBack;
import com.zy.eureka.consumer.conf.feign.loglevel.FeignLogLevelConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @AUTHOR zhangy
 * 2019-09-18  20:57
 */
@FeignClient(name = "${provider.name}", configuration = FeignLogLevelConfiguration.class ,fallback = ServiceProviderFallBack.class  )
public interface EurekServerProvider {


    @GetMapping(value = "/api/{name}")
    String getName(@PathVariable String name );
}
