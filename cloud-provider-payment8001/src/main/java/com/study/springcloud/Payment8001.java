package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *  @author yxb
 *  约定  >   配置  >   编码
 *  EnableDiscoveryClient  服务发现注解
 *  EnableEurekaClient     Eureka服务客户端注解
 *  微服务开发流程：
 *      1、建module
 *      2、改pom文件
 *      3、写yml或propotites
 *      4、编写主启动类
 *      5、编写业务实现
 * */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class Payment8001 {
    public static void main(String[] args) {
         SpringApplication.run(Payment8001.class, args);
    }
}
