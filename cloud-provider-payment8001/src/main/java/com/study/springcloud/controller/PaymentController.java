package com.study.springcloud.controller;


import com.study.springcloud.common.CommonResult;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;
    
    /** 服务发现 */
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "payment/save")
    public CommonResult savePayment(@RequestBody Payment payment){
        Long result = paymentService.save(payment);
        log.info("保存结果"+result);
        if(result > 0){
            return new CommonResult(200,"插入数据成功,serverPort"+serverPort,result);
        }else {
            return new CommonResult(400,"插入数据失败,serverPort"+serverPort,result);
        }
    }

    @GetMapping(value = "payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")  long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果"+payment);
        if (payment != null) {
            return new CommonResult(200, "查询数据成功,serverPort"+serverPort, payment);
        } else {
            return new CommonResult(400, "查询数据失败,serverPort"+serverPort, null);
        }
    }
    
    @GetMapping("/payment/discovery")
    public Object discoveryClient(){
        // 获取微服务列表
        List<String> services = discoveryClient.getServices();
        for (int i = 0; i < services.size(); i++) {
            log.info(services.get(i));
        }
        // 获取某个微服务实例信息
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }


}
