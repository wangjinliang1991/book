package com.example.book.transaction.mediator;

import com.example.book.transaction.colleague.AbstractCustomer;
import com.example.book.transaction.colleague.Buyer;
import com.example.book.transaction.colleague.Payer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjl
 * @date 2024/8/27
 */
@Component
public class Mediator extends AbstractMediator{
    public static Map<String, Map<String, AbstractCustomer>> customerInstances = new ConcurrentHashMap<>();

    @Override
    public void messageTransfer(String orderId, String targetCustomer, AbstractCustomer customer, String payResult) {
        if (customer instanceof Buyer) {
            AbstractCustomer buyer = customerInstances.get(orderId).get("buyer");
            System.out.println("Friend pays on behalf: "+buyer.getCustomerName() + " transfer orderId "+ orderId+" to customer "+targetCustomer+" to pay");
        } else if (customer instanceof Payer) {
            AbstractCustomer payer = customerInstances.get(orderId).get("payer");
            System.out.println("payment completed on behalf "+payer.getCustomerName()+" completed orderId "+orderId+" pay, notify "+targetCustomer+", pay result: "+payResult);
            customerInstances.remove(orderId);
        }
    }

    public void addInstance(String orderId, HashMap<String, AbstractCustomer> map) {
        customerInstances.put(orderId, map);
    }
}
