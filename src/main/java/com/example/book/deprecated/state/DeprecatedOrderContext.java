package com.example.book.deprecated.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangjl
 * @date 2024/8/18
 */
@Component
public class DeprecatedOrderContext {
    private DeprecatedAbstractOrderState currentState;

    @Autowired
    private DeprecatedCreateOrder deprecatedCreateOrder;

    //设置当前的订单状态
    public void setCurrentState(DeprecatedAbstractOrderState currentState) {
        this.currentState = currentState;
    }

    public DeprecatedOrder createOrder(String orderId,String productId) {
        this.currentState = this.deprecatedCreateOrder;
        DeprecatedOrder order = currentState.createOrder(orderId, productId, this);
        return order;
    }

    public DeprecatedOrder payOrder(String orderId) {
        this.currentState = this.deprecatedCreateOrder;
        DeprecatedOrder order = currentState.payOrder(orderId, this);
        return order;
    }

    public DeprecatedOrder sendOrder(String orderId) {
        this.currentState = this.deprecatedCreateOrder;
        DeprecatedOrder order = currentState.sendOrder(orderId, this);
        return order;
    }

    public DeprecatedOrder receiveOrder(String orderId) {
        this.currentState = this.deprecatedCreateOrder;
        DeprecatedOrder order = currentState.sendOrder(orderId, this);
        return order;
    }
}
