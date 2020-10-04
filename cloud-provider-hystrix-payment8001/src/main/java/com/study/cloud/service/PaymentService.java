package com.study.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    // =========================服务降级==================================

    public String paymentOk(Long id){
        return "线程池："   +Thread.currentThread().getName() + "paymentOk,id" + id;
    }

    /**
     * 激活服务降级注解
     * 处理超时异常或者程序异常都由paymentErrorHandler方法处理
     * defaultFallback 服务降级所要执行方法
     * HystrixProperty 设置服务降级时的方法阈值
     * */
    @HystrixCommand(fallbackMethod = "paymentErrorHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    public String paymentError(Long id){
        int time = 3;
        // int i = 10/0;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："   +Thread.currentThread().getName() + "paymentError,id" + id +"耗时"+time;
    }

    public String paymentErrorHandler(Long id){
        return "线程池："   +Thread.currentThread().getName() + "paymentError,id" + id +"兜底方法";
    }

    // =========================服务熔断==================================
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
       //是否开启断路
       @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
       //请求次数
       @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
       //时间窗口期、时间范围
       @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
       // 失败率达到多少后跳闸
       @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String paymentCircuitBreaker(Long id){
        if(id < 0){
            throw new RuntimeException("ID不能为负数！");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Long id){
        return Thread.currentThread().getName() + "\t" + "id不能为负，请稍后重试" + id;
    }

}
