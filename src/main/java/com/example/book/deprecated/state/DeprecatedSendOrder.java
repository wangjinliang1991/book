package com.example.book.deprecated.state;

import com.example.book.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangjl
 * @date 2024/8/18
 */
@Component
public class DeprecatedSendOrder extends DeprecatedAbstractOrderState{
    @Autowired
    private RedisCommonProcessor redisCommonProcessor;
    @Autowired
    private DeprecatedReceiveOrder deprecatedReceiveOrder;

    @Override
    protected Order sendOrder(String orderId, DeprecatedOrderContext context) {
        Order order = (Order) redisCommonProcessor.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_SEND)) {
            throw new UnsupportedOperationException("order state should be ORDER_WAIT_SEND, but it's state is: " + order.getState());
        }
        order.setState(ORDER_WAIT_RECEIVE);
        redisCommonProcessor.set(orderId, order);
        context.setCurrentState(this.deprecatedReceiveOrder);
        super.notifyObserver(orderId,ORDER_WAIT_RECEIVE);
        return order;
    }
}
