package com.example.book.deprecated.state;

import com.example.book.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangjl
 * @date 2024/8/18
 */
@Component
public class DeprecatedCreateOrder extends DeprecatedAbstractOrderState{
    @Autowired
    private RedisCommonProcessor redisCommonProcessor;
    @Autowired
    private DeprecatedPayOrder deprecatedPayOrder;

    @Override
    protected DeprecatedOrder createOrder(String orderId, String productId, DeprecatedOrderContext context) {
        //订单创建完成，设置为待支付
        DeprecatedOrder order = DeprecatedOrder.builder()
                .orderId(orderId)
                .productId(productId)
                .state(ORDER_WAIT_PAY)
                .build();
        // 新订单存入redis 15min 失效
        redisCommonProcessor.set(orderId,order,900);
        //顶大创建完成，设置上下文的currenState为待支付
        context.setCurrentState(this.deprecatedPayOrder);
        return null;
    }
}
