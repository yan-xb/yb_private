package com.study.springcloud.controller;

import com.study.springcloud.common.CommonResult;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.lb.LoadBalancer;
import com.study.springcloud.lb.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {


    /**注入并实例化远程调用 RestTemplate */
    @Resource
    private RestTemplate restTemplate;
    /**单机版地址写死 */
    // private static final String PAYMENT_URL = "http://localhost:8001";
    /**微服务版只认在注册中心的微服务名称 */
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    /**引入自己编写的负载均衡接口类 */
    @Resource
    private LoadBalancer loadBalancer;

    /**引入客户端服务发现 */
    @Resource
    private  DiscoveryClient discoveryClient;

    @GetMapping(value = "consumer/payment/save")
    public CommonResult<Payment> save(Payment payment){
       return restTemplate.postForObject(PAYMENT_URL+"payment/save",payment,CommonResult.class);
    }

    @GetMapping(value = "consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping(value = "consumer/paymentForEntity/save")
    public CommonResult<Payment> saveForEntity(Payment payment){
        CommonResult commonResult = restTemplate.postForEntity(PAYMENT_URL + "payment/save", payment, CommonResult.class).getBody();
        return commonResult;
    }

    @GetMapping(value = "consumer/paymentForEntity/get/{id}")
    public CommonResult<Payment> getPaymentForEntity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return  new CommonResult();
        }
    }

    @GetMapping(value = "consumer/payment/lb")
    public  String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        String stringPort = restTemplate.getForObject(uri+"/payment/lb",String.class);
        return stringPort;
    }

    /**
     * zipkin+sleuth
     * */
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin/", String.class);
        return result;
    }

}
