package com.study.cloud.service.impl;

import com.study.cloud.service.PaymentService;
import org.springframework.stereotype.Component;

/**
 * Component 配合此注解将消费端统一异常处理服务降级类添加到组件中
 * */
@Component
public class PaymentFallBackService implements PaymentService {
    @Override
    public String paymentOk(Long id) {
        return "paymentOk ------ 消费端统一异常处理服务降级类";
    }

    @Override
    public String paymentError(Long id) {
        return "paymentError ------ 消费端统一异常处理类";
    }
}
