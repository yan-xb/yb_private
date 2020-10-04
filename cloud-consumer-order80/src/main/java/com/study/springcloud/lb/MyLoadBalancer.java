package com.study.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 将此实现类加入到组件中
 * */
@Component
public class MyLoadBalancer implements LoadBalancer {

    /**元子类*/
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final int getAndIncrement(){
        int current;
        int next;
        do{
            //获取当前原子类值 0
            current = this.atomicInteger.get();
            // 最大的数字整形 2147483647 做以下判断防止越界、溢出
            next = current >= 2147483647 ? 0 : current+1;
        } while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("第几次访问次数next:::::::::::::" +next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {

        int index = getAndIncrement() % serviceInstances.size();

        //返回处理请求的具体服务机器
        return serviceInstances.get(index);
    }
}
