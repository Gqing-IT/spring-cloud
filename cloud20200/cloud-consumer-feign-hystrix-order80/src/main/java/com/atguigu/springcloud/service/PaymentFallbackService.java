package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "解耦的paymentInfo_OK的服务降级";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "解耦的paymentInfo_TimeOut的服务降级";
    }
}
