package com.example.book.transaction.mediator;

import com.example.book.transaction.colleague.AbstractCustomer;
import com.example.book.transaction.colleague.Buyer;
import com.example.book.transaction.colleague.Payer;

/**
 * @author wangjl
 * @date 2024/8/27
 */
public class Mediator extends AbstractMediator{
    private AbstractCustomer buyer;
    private AbstractCustomer payer;

    public void setBuyer(AbstractCustomer buyer) {
        this.buyer = buyer;
    }

    public void setPayer(AbstractCustomer payer) {
        this.payer = payer;
    }

    @Override
    public void messageTransfer(String orderId, String targetCustomer, AbstractCustomer customer, String payResult) {
        if (customer instanceof Buyer) {
            System.out.println("Friend pays on behalf: "+buyer.getCustomerName() + " transfer orderId "+ orderId+" to customer "+targetCustomer+" to pay");
        } else if (customer instanceof Payer) {
            System.out.println("payment completed on behalf "+payer.getCustomerName()+" completed orderId "+orderId+" pay, notify "+targetCustomer+", pay result: "+payResult);
        }
    }
}
