package com.zy.eureka.consumer.conf.feign.loglevel;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @AUTHOR zhangy
 * 2019-09-22  15:54
 */
@Configuration
public class FeignLogLevelConfiguration {

    /**
     * Feign 的日志级别配置
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel () {
        return Logger.Level.FULL;
    }
}
