package com.study.cloud.service;

import com.study.springcloud.common.CommonResult;
import com.study.springcloud.entities.Payment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * FeignClient 找要调用的微服务
 * */
@Component
@FeignClient(value = "cloud-payment-service")
public interface OpenFeignservice {
    /**
     * GetMapping 微服务调用地址
     * */
    @GetMapping(value = "consumer/payment/get/{id}")
     CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
