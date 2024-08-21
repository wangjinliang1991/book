package com.example.book.deprecated.state;

import com.example.book.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangjl
 * @date 2024/8/18
 */
@Component
public class DeprecatedPayOrder extends DeprecatedAbstractOrderState{
    @Autowired
    private RedisCommonProcessor redisCommonProcessor;
    @Autowired
    private DeprecatedSendOrder deprecatedSendOrder;
    @Override
    protected DeprecatedOrder payOrder(String orderId,DeprecatedOrderContext context) {
        DeprecatedOrder order = (DeprecatedOrder) redisCommonProcessor.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_PAY)) {
            throw new UnsupportedOperationException("order state should be ORDER_WAIT_PAY, but now it's state is: "+order.getState());
        }
        //todo 支付
        //完成后，修改订单状态为待发货，更新redis缓存
        order.setState(ORDER_WAIT_SEND);
        redisCommonProcessor.set(orderId,order);
        // 发送订单支付event
        // 订单支付完成，修改状态
        context.setCurrentState(this.deprecatedSendOrder);
        return null;
    }
}
