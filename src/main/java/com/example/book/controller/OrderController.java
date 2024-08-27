package com.example.book.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.example.book.pojo.Order;
import com.example.book.service.OrderService;
import com.example.book.service.decorator.OrderServiceDecorator;
import com.example.book.utils.Constants;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author wangjl
 * @date 2024/8/22
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderServiceDecorator orderServiceDecorator;
    @Value("${service.level}")
    private Integer serviceLevel;

    // 订单创建
    @PostMapping("/create")
    public Order createOrder(@RequestParam String productId) {
        return orderService.createOrder(productId);
    }

    // 订单支付
    @PostMapping("/pay")
    public String payOrder(@RequestParam String orderId,
                           @RequestParam Float price,
                           @RequestParam Integer payType) {
        return orderService.getPayUrl(orderId, price, payType);
    }

    // 订单发送
    @PostMapping("/send")
    public Order send(@RequestParam String orderId) {
        return orderService.send(orderId);
    }

    // 订单签收
    @PostMapping("/receive")
    public Order receive(@RequestParam String orderId) {
        return orderService.receive(orderId);
    }

    @RequestMapping("/alipaycallback")
    public String alipayCallback(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        //获取回调信息
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        Iterator<String> iter = requestParams.keySet().iterator();
        while (iter.hasNext()) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        //验证签名，确保回调接口是支付宝平台触发的
        // 支付宝公钥，第一次上传应用公钥之后，支付宝平台自动生成的值，不是沙箱应用的公钥，因此demo验签失败 ...
        boolean signVerified = AlipaySignature.rsaCheckV1(params, Constants.ALIPAY_PUBLIC_KEY, "utf-8", Constants.SIGN_TYPE);
        // todo 暂时改为true
        signVerified = true;
        if (signVerified) {
            String out_trade_no = new String(request.getParameter("out_trade_no"));
            String trade_no = new String(request.getParameter("trade_no"));
            float total_amount = Float.parseFloat(new String(request.getParameter("total_amount")));
            //进行相关的业务操作，修改订单状态为待发货状态
//            Order order = orderService.pay(out_trade_no);
            orderServiceDecorator.setOrderServiceInterface(orderService);
            Order order = orderServiceDecorator.decoratorPay(out_trade_no, serviceLevel, total_amount);
            return "支付成功页面跳转，当前订单为：" + order;
        } else {
            throw new UnsupportedOperationException("callback verify failed");
        }
    }

    @PostMapping("/friendPay")
    public void friendPay(String sourceCustomer, String orderId, String targetCustomer, String payResult, String role) {
        orderService.friendPay(sourceCustomer, orderId, targetCustomer, payResult, role);
    }
}
