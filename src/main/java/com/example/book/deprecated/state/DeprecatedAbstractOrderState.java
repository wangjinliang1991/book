package com.example.book.deprecated.state;

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

    protected DeprecatedOrder createOrder(String orderId,String productId,DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    protected DeprecatedOrder payOrder(String orderId,DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    protected DeprecatedOrder sendOrder(String orderId,DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    protected DeprecatedOrder receiveOrder(String orderId,DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

}
