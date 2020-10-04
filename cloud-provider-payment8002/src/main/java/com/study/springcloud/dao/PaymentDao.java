package com.study.springcloud.dao;

import com.study.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 持久层添加注解
 * @Repository  不推荐使用 有时插入会有问题
 */
@Mapper
public interface PaymentDao {

    Long save(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
