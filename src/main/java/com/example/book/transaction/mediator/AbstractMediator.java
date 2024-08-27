package com.example.book.transaction.mediator;

import com.example.book.transaction.colleague.AbstractCustomer;

/**
 * Mediator抽象中介者
 * @author wangjl
 * @date 2024/8/27
 */
public abstract class AbstractMediator {
    /**
     * 消息交互
     * @param orderId 用户的订单ID
     * @param targetCustomer 目标用户，比如说张三请李四帮忙支付，那么李四便是目标用户
     * @param customer 抽象同事类 中介者需要根据该参数确定调用者的角色——购买者或帮忙支付者
     * @param payResult 只有支付成功后此参数才不为null
     */
    public abstract void messageTransfer(String orderId, String targetCustomer, AbstractCustomer customer, String payResult);
}
