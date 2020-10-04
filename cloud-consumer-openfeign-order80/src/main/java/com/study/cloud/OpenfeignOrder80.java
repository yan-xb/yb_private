package com.study.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * EnableFeignClients 开启FeignClient
 * */
@SpringBootApplication
@EnableFeignClients
public class OpenfeignOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(OpenfeignOrder80.class,args);
    }
}
