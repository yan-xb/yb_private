package com.study.cloud.service;

import com.study.cloud.service.impl.PaymentFallBackService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



/**
 * Component 将接口添加到组件中
 * FeignClient(value = "cloud-provider-hystrix-payment") 统一调用消费者暴露出的服务接口
 * FeignClient(value = "cloud-provider-hystrix-payment",fallback = "PaymentFallBackService")
 * 添加服务降级配置：三种情况下：运行时异常，超时异常，宕机
 * */
@Component
@FeignClient(value = "cloud-provider-hystrix-payment")
@FeignClient(value = "cloud-provider-hystrix-payment",fallback = PaymentFallBackService.class)
public interface PaymentService {
    @GetMapping(value = "payment/hystrix/ok/{id}")
     String paymentOk(@PathVariable("id") Long id);


    @GetMapping(value = "payment/hystrix/error/{id}")
     String paymentError(@PathVariable("id") Long id);



}
