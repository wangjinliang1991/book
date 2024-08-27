package com.example.book.transaction.colleague;

import com.example.book.transaction.mediator.AbstractMediator;

/**
 * @author wangjl
 * @date 2024/8/27
 */
public class Payer extends AbstractCustomer{

    public Payer(AbstractMediator mediator, String orderId, String customerName) {
        super(mediator, orderId, customerName);
    }

    @Override
    public void messageTransfer(String orderId, String targetCustomer, String payResult) {
        super.mediator.messageTransfer(orderId,targetCustomer,this,payResult);
    }
}
