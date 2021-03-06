package com.example.dididemo.controller;

import com.alipay.api.AlipayApiException;
import com.example.dididemo.entity.AlipayBean;
import com.example.dididemo.service.PayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/* 订单接口 */

@RestController()
@RequestMapping("order")
public class OrderController {
    //调用支付服务
    @Resource
    private PayService payService;
    /*阿里支付*/
    /*@PostMapping(value = "alipay")
    public String alipay(String out_trade_no,String subject,String total_amount,String body) throws AlipayApiException {
        return  payService.aliPay(new AlipayBean()
                .setBody(body)
                .setOut_trade_no(out_trade_no)
                .setTotal_amount(new StringBuffer().append(total_amount))
                .setSubject(subject));
    }*/
}
