package com.example.book.service;

import com.example.book.ordermanagement.command.OrderCommand;
import com.example.book.ordermanagement.command.invoker.OrderCommandInvoker;
import com.example.book.ordermanagement.state.OrderState;
import com.example.book.ordermanagement.state.OrderStateChangeAction;
import com.example.book.pay.facade.PayFacade;
import com.example.book.pojo.Order;
import com.example.book.transaction.colleague.AbstractCustomer;
import com.example.book.transaction.colleague.Buyer;
import com.example.book.transaction.colleague.Payer;
import com.example.book.transaction.mediator.Mediator;
import com.example.book.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author wangjl
 * @date 2024/8/22
 */
@Service
public class OrderService implements OrderServiceInterface{
    @Autowired
    private StateMachine<OrderState, OrderStateChangeAction> orderStateMachine;

    @Autowired
    private StateMachinePersister<OrderState,OrderStateChangeAction,String> stateMachinePersister;

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;
    @Autowired
    private OrderCommand orderCommand;

    @Autowired
    private PayFacade payFacade;

    @Autowired
    private Mediator mediator;

    public Order createOrder(String productId) {
        //订单生成的逻辑
        String orderId = "OID" + productId;
        Order order = Order.builder()
                .orderId(orderId)
                .productId(productId)
                .orderState(OrderState.ORDER_WAIT_PAY)
                .build();
        redisCommonProcessor.set(orderId,order,900);
        OrderCommandInvoker invoker = new OrderCommandInvoker();
        invoker.invoke(orderCommand,order);
        return order;
    }

    // 支付，雷点
    public  Order pay(String orderId) {
        Order order = (Order) redisCommonProcessor.get(orderId);
        Message message = MessageBuilder
                .withPayload(OrderStateChangeAction.PAY_ORDER)
                .setHeader("order",order)
                .build();
        // message传给状态机
        if (changeSateAction(message, order)) {
            return order;
        }
        return null;
    }

    public  Order send(String orderId) {
        Order order = (Order) redisCommonProcessor.get(orderId);
        Message message = MessageBuilder
                .withPayload(OrderStateChangeAction.SEND_ORDER)
                .setHeader("order",order)
                .build();
        // message传给状态机
        if (changeSateAction(message, order)) {
            return order;
        }
        return null;
    }

    public  Order receive(String orderId) {
        Order order = (Order) redisCommonProcessor.get(orderId);
        Message message = MessageBuilder
                .withPayload(OrderStateChangeAction.RECEIVE_ORDER)
                .setHeader("order",order)
                .build();
        // message传给状态机
        if (changeSateAction(message, order)) {
            return order;
        }
        return null;
    }

    private boolean changeSateAction(Message message, Order order) {
        try {
            orderStateMachine.start();
            //从redis中读取状态机，key自定义
            stateMachinePersister.restore(orderStateMachine, order.getOrderId() + "STATE");
            // message 发给 listener
            boolean res = orderStateMachine.sendEvent(message);
            stateMachinePersister.persist(orderStateMachine, order.getOrderId() + "STATE");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            orderStateMachine.stop();
        }
        return false;
    }


    public String getPayUrl(String orderId, Float price, Integer payType) {
        Order order = (Order) redisCommonProcessor.get(orderId);
        order.setPrice(price);
        return payFacade.pay(order, payType);
    }

    public void friendPay(String sourceCustomer, String orderId, String targetCustomer, String payResult, String role) {
        //创建中介者
        Buyer buyer = new Buyer( mediator, orderId,sourceCustomer);
        Payer payer = new Payer( mediator, orderId,sourceCustomer);
        HashMap<String, AbstractCustomer> map = new HashMap<>();
        map.put("buyer", buyer);
        map.put("payer",payer);
        mediator.addInstance(orderId,map);
        if (role.equals("B")) {
            buyer.messageTransfer(orderId,targetCustomer,payResult);
        } else if (role.equals("P")) {
            payer.messageTransfer(orderId,targetCustomer,payResult);
        }
    }
}
