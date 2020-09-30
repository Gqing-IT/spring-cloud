package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("payment/create")
    public CommonResult creat(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("******插入结果"+i);

        if(i>0){
            return new CommonResult(200,"插入数据库成功serverPort="+serverPort,i);
        }else{
            return new CommonResult(444,"插入数据库失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPayment(id);
        log.info("*******查询结果"+payment);
        if(payment!=null){
            return new CommonResult(200,"查询成功serverPort="+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录，查询ID"+id,null);
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
