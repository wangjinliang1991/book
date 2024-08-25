package com.example.book.pay.facade;

import com.example.book.pay.context.PayContext;
import com.example.book.pay.strategy.factory.PayContextFactory;
import com.example.book.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 门面
 * @author wangjl
 * @date 2024/8/23
 */
@Component
public class PayFacade {
    @Autowired
    private PayContextFactory contextFactory;
    public String pay(Order order, Integer payType) {
        PayContext context = contextFactory.getContext(payType);
        return context.execute(order);
    }
}
