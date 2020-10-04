package com.study.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.study.cloud.service.PaymentService;
import javafx.beans.DefaultProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * DefaultProperties 激活全局服务降级方法配置注解 配合@HystrixCommand实现
 * */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentErrorGloble")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "consumer/payment/ok/{id}")
    public String consumerPayment_OK(@PathVariable("id") Long id){
        return paymentService.paymentOk(id);
    }

    @GetMapping(value = "consumer/payment/error/{id}")
    public String consumerPayment_Error(@PathVariable("id") Long id){
        return paymentService.paymentError(id);
    }

    /**
     * 自定义激活服务降级注解
     * 处理超时异常或者程序异常都由paymentErrorHandler方法处理
     * defaultFallback 服务降级所要执行方法
     * HystrixProperty 设置服务降级时的方法阈值
     * */
    // @HystrixCommand(fallbackMethod = "paymentErrorHandler",commandProperties = {
    //         @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    // })
    @HystrixCommand
    @GetMapping(value = "consumer/payment/hystrix/error/{id}")
    public String paymentError(Long id){
        int i = 10/0;
        return paymentService.paymentError(id);
    }

    public String paymentErrorHandler(Long id){
        return "消费者80服务降级方法";
    }

    public String paymentErrorGloble(Long id){
        return "消费者80服务全局 降级方法";
    }
}
