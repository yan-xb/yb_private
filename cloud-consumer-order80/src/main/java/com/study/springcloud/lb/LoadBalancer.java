package com.study.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    /**
     * 得到服务系统总数
     * */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);

}
