package com.study.cloud.service.impl;

import com.study.cloud.service.PaymentService;
import com.study.springcloud.common.CommonResult;
import com.study.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"openFeign的兜底方法！");
    }
}
