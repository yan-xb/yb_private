package com.study.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.cloud.service.PaymentService;
import com.study.springcloud.common.CommonResult;
import com.study.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.annotation.Resources;

@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://cloud-alibaba-provider-payment";

    @Resource
    public RestTemplate restTemplate;

    @Resource
    public PaymentService paymentService;

    @RequestMapping("/consumer/fallback/{id}")
    // @SentinelResource(value = "fallback")   //什么都不配置
    // @SentinelResource(value = "fallback",fallback = "handlerFallback") //只负责代码中业务类异常
    // @SentinelResource(value = "fallback",blockHandler = "blockHandler") //只负责sentinel控制台中的违规配置
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})//忽略异常
    public CommonResult<Payment> fallback(@PathVariable Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL+"/paymentSQL/"+id,CommonResult.class,id);
        if(id == 4){
            throw new IllegalArgumentException("非法参数异常！");
        }else if(result.getData() == null){
            throw new NullPointerException("空指针异常！");
        }
        return result;
    }

    public CommonResult<Payment> handlerFallback(Long id, Throwable tb)
    {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"业务异常兜底回滚方法！异常内容："+tb.getMessage(),payment);
    }

    public CommonResult<Payment> blockHandler(Long id, BlockException be)
    {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445,"限流访问兜底回滚方法！BlockException异常内容："+be.getMessage(),payment);
    }

    @GetMapping("/consumer/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id)
    {
        return paymentService.paymentSQL(id);
    }


}
