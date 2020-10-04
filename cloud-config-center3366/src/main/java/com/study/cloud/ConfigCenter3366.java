package com.study.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigCenter3366 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenter3366.class,args);
    }
}
