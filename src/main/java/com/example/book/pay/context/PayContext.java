package com.example.book.pay.context;

import com.example.book.pay.strategy.PayStrategyInterface;
import com.example.book.pojo.Order;

/**
 * 策略的环境类
 * @author wangjl
 * @date 2024/8/22
 */
public class PayContext extends AbstractPayContext{
    private PayStrategyInterface payStrategy;

    public PayContext(PayStrategyInterface payStrategy) {
        this.payStrategy = payStrategy;
    }

    @Override
    public String execute(Order order) {
        return this.payStrategy.pay(order);
    }
}
