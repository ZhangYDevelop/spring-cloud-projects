package com.zy.eureka.consumer.conf.feign.fallback;

import com.zy.eureka.consumer.client.EurekServerProvider;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR zhangy
 * 2019-09-22  16:32
 */
@Component
public class ServiceProviderFallBack implements EurekServerProvider {
    @Override
    public String getName(String name) {
        return "FeignClient 超时 ：服务被降级了";
    }
}
