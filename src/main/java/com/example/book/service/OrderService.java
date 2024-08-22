package com.example.book.service;

import com.example.book.ordermanagement.state.OrderState;
import com.example.book.ordermanagement.state.OrderStateChangeAction;
import com.example.book.pojo.Order;
import com.example.book.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

/**
 * @author wangjl
 * @date 2024/8/22
 */
@Service
public class OrderService {
    @Autowired
    private StateMachine<OrderState, OrderStateChangeAction> orderStateMachine;

    @Autowired
    private StateMachinePersister<OrderState,OrderStateChangeAction,String> stateMachinePersister;

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    public Order createOrder(String productId) {
        //订单生成的逻辑
        String orderId = "OID" + productId;
        Order order = Order.builder()
                .orderId(orderId)
                .productId(productId)
                .orderState(OrderState.ORDER_WAIT_PAY)
                .build();
        redisCommonProcessor.set(orderId,order,900);
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


}
