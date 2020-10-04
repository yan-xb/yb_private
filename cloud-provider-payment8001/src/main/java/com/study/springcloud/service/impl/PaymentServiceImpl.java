package com.study.springcloud.service.impl;

import com.study.springcloud.entities.Payment;
import com.study.springcloud.dao.PaymentDao;
import com.study.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    /**
     * Resource  springboot中的注解
     * Autowired  spring中的注解
     * */
    @Resource
    private PaymentDao paymentDao;

    @Override
    public Long save(Payment payment){
        return paymentDao.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
}
