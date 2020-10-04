package com.study.cloud.controller;

import com.study.cloud.service.OpenFeignservice;
import com.study.springcloud.common.CommonResult;
import com.study.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OpenfeignController {
    @Resource
    private OpenFeignservice openFeignservice;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return openFeignservice.getPaymentById(id);
    }

}
