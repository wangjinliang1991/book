package com.example.book.deprecated.state;

import com.example.book.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangjl
 * @date 2024/8/18
 */
@Component
public class DeprecatedReceiveOrder extends DeprecatedAbstractOrderState{
    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Override
    protected DeprecatedOrder receiveOrder(String orderId, DeprecatedOrderContext context) {
        DeprecatedOrder order = (DeprecatedOrder) redisCommonProcessor.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_RECEIVE)) {
            throw new UnsupportedOperationException("order state should be ORDER_WAIT_RECEIVE, but it's state is: " + order.getState());
        }
        order.setState(ORDER_FINISH);
        redisCommonProcessor.remove(orderId);
        super.notifyObserver(orderId,ORDER_FINISH);
        return order;
    }
}
