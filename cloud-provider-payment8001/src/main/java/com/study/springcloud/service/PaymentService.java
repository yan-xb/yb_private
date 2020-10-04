package com.study.springcloud.service;

import com.study.springcloud.entities.Payment;

public interface PaymentService {

    public Long save(Payment payment);

    public Payment getPaymentById(Long id);

}
