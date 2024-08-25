package com.example.book.pay.strategy;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.book.pojo.Order;
import com.example.book.utils.Constants;

/**
 * 支付宝
 * @author wangjl
 * @date 2024/8/22
 */
public class AlipayStrategy implements PayStrategyInterface{
    @Override
    public String pay(Order order) {
        AlipayClient alipayClient = new DefaultAlipayClient(Constants.ALIPAY_GATEWAY,Constants.APP_ID,
                Constants.APP_PRIVATE_KEY,
                "json","UTF-8",
                Constants.ALIPAY_PUBLIC_KEY,Constants.SIGN_TYPE);
        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        payRequest.setReturnUrl(Constants.CALLBACK_URL);
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        // 设置订单绝对超时时间
        model.setSubject("mytest");
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        model.setMerchantOrderNo(order.getOrderId());
        model.setOutTradeNo(order.getOrderId());
        model.setTotalAmount(String.valueOf(order.getPrice()));
        model.setBody("商品描述");
        payRequest.setBizModel(model);

        try {
            return alipayClient.pageExecute(payRequest, "GET").getBody();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Alipay failed!" + e);
        }
    }
}
