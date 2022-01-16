package com.example.dididemo.service;

import com.alipay.api.AlipayApiException;
import com.example.dididemo.entity.AlipayBean;
import com.example.dididemo.util.AlipayUtil;
import org.springframework.stereotype.Service;

/* 支付服务 */
@Service(value = "alipayOrderService")
public class PayServiceImpl implements PayService {
    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return AlipayUtil.connect(alipayBean);
    }
}