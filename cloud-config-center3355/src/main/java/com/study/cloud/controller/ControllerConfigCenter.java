package com.study.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RefreshScope 刷新注解，在动态刷新配置中心时使用
 * 此注解必须配合一次post请求使用刷新系统，命令如下：
 * curl -X  POST  "http://localhost:3355/actuator/refresh"
 *
 * */
@RestController
@RefreshScope
public class ControllerConfigCenter {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "getConfigInfo")
    private String getConfigInfo(){
        return configInfo;
    }

}
