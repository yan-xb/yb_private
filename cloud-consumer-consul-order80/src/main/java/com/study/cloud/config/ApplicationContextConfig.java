package com.study.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    // 注释LoadBalanced注解，自己手动写一个轮询算法替代
    // @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
