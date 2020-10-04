package com.study.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 配置类注解
 * */
@Configuration
public class ApplicationContextConfig {

    /**
     * 微服务远程调用模板 对httpClient的在封装
     * LoadBalanced 开启微服务ribbon负载均衡机制 若不加此注解会不知道调用服务名下的哪台主机
     * */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
