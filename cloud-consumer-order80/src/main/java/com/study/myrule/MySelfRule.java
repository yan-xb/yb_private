package com.study.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 替换ribbon默认负载均衡（轮训规则）类注意要点
 *  1.必须将此类及子类包单独放在项目默认扫描包外
 *  2.在项目启动类上添加注解@RibbonClint(name = "服务名称"，configuration=自己定义轮询策略类)
 * */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        // 将轮循策略改成随机
        return new RandomRule();
    }

}
