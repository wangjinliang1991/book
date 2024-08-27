package com.example.book.transaction.colleague;

import com.example.book.transaction.mediator.AbstractMediator;

/**
 * 抽象同事类
 * @author wangjl
 * @date 2024/8/27
 */
public abstract class AbstractCustomer {
    //关联中介者
    public AbstractMediator mediator;
    //订单ID
    public String orderId;
    //当前用户信息
    public String customerName;

    public AbstractCustomer(AbstractMediator mediator, String orderId, String customerName) {
        this.mediator = mediator;
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    // 和中介者的信息交互方法
    public abstract void messageTransfer(String orderId, String targetCustomer, String payResult);
}
