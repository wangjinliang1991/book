package com.example.book.controller;

import com.example.book.deprecated.state.Order;
import com.example.book.service.DeprecatedOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjl
 * @date 2024/8/18
 */
@RestController
@RequestMapping("/deprecated/order")
public class DeprecatedOrderController {
    @Autowired
    private DeprecatedOrderService deprecatedOrderService;

    // 订单创建
    @PostMapping("/create")
    public Order createOrder(@RequestParam String productId) {
        return deprecatedOrderService.createOrder(productId);
    }

    // 订单支付
    @PostMapping("/pay")
    public Order payOrder(@RequestParam String orderId) {
        return deprecatedOrderService.pay(orderId);
    }

    // 订单发送
    @PostMapping("/send")
    public Order send(@RequestParam String orderId) {
        return deprecatedOrderService.send(orderId);
    }

    // 订单签收
    @PostMapping("/receive")
    public Order receive(@RequestParam String orderId) {
        return deprecatedOrderService.receive(orderId);
    }
}
