package com.study.cloud.controller;

import com.study.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "payment/hystrix/ok/{id}")
    public String paymentOk(@PathVariable("id") Long id){
        String s = paymentService.paymentOk(id);
        log.info(s);
        return s;
    }

    @GetMapping(value = "payment/hystrix/error/{id}")
    public String paymentError(@PathVariable("id") Long id){
        String s = paymentService.paymentError(id);
        log.info(s);
        return s;
    }

    // ==========================  服务熔断 ===================

    @GetMapping(value = "payment/circuit/{id}")
    public String paymentCircuit(@PathVariable("id") Long id){
        String s = paymentService.paymentCircuitBreaker(id);
        log.info(s);
        return s;
    }
}
