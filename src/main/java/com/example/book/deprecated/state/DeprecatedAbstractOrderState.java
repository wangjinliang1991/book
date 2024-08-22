package com.example.book.deprecated.state;

import com.example.book.deprecated.observer.DeprecatedAbstractObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjl
 * @date 2024/8/18
 */
public abstract class DeprecatedAbstractOrderState {
    //订单状态定义，待支持、待发货、待收货、订单完成
    protected final String ORDER_WAIT_PAY = "ORDER_WAIT_PAY";
    protected final String ORDER_WAIT_SEND = "ORDER_WAIT_SEND";
    protected final String ORDER_WAIT_RECEIVE = "ORDER_WAIT_RECEIVE";
    protected final String ORDER_FINISH = "ORDER_FINISH";
    protected final List<DeprecatedAbstractObserver> observerList = new ArrayList<>();

    public void addObserver(DeprecatedAbstractObserver observer) {
        this.observerList.add(observer);
    }

    public void removeObserver(DeprecatedAbstractObserver observer) {
        this.observerList.remove(observer);
    }

    public void notifyObserver(String orderId, String orderState) {
        for (DeprecatedAbstractObserver observer : this.observerList) {
            observer.orderStateHandle(orderId,orderState);
        }
    }
    protected Order createOrder(String orderId, String productId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    protected Order payOrder(String orderId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    protected Order sendOrder(String orderId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    protected Order receiveOrder(String orderId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

}
