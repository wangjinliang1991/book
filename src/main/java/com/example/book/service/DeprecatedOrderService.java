package com.example.book.service;

import com.example.book.deprecated.state.DeprecatedOrder;
import com.example.book.deprecated.state.DeprecatedOrderContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjl
 * @date 2024/8/18
 */
@Service
public class DeprecatedOrderService {
    @Autowired
    private DeprecatedOrderContext orderContext;

    public DeprecatedOrder createOrder(String productId) {
        //订单生成的逻辑
        String orderId = "OID" + productId;
        return orderContext.createOrder(orderId, productId);
    }

    public DeprecatedOrder pay(String orderId) {
        return orderContext.payOrder(orderId);
    }

    public DeprecatedOrder send(String orderId) {
        return orderContext.sendOrder(orderId);
    }


    public DeprecatedOrder receive(String orderId) {
        return orderContext.receiveOrder(orderId);
    }
}
