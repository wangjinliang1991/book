package com.example.book.ordermanagement.listener;

import com.example.book.ordermanagement.state.OrderState;
import com.example.book.ordermanagement.state.OrderStateChangeAction;
import com.example.book.pojo.Order;
import com.example.book.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * @author wangjl
 * @date 2024/8/22
 */
@Component
@WithStateMachine(name = "orderStateMachine")
public class OrderStateListener {
    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @OnTransition(source = "ORDER_WAIT_PAY", target = "ORDER_WAIT_SEND")
    public boolean payToSend(Message<OrderStateChangeAction> message) {
        Order order = (Order) message.getHeaders().get("order");
        if (order.getOrderState() != OrderState.ORDER_WAIT_PAY) {
            throw new UnsupportedOperationException("order state error!");
        }
        //支付成功，修改状态
        order.setOrderState(OrderState.ORDER_WAIT_SEND);
        redisCommonProcessor.set(order.getOrderId(),order);
        // todo 命令模式处理
        return true;
    }

    @OnTransition(source = "ORDER_WAIT_SEND", target = "ORDER_WAIT_RECEIVE")
    public boolean sendToReceive(Message<OrderStateChangeAction> message) {
        Order order = (Order) message.getHeaders().get("order");
        if (order.getOrderState() != OrderState.ORDER_WAIT_SEND) {
            throw new UnsupportedOperationException("order state error!");
        }
        order.setOrderState(OrderState.ORDER_WAIT_RECEIVE);
        redisCommonProcessor.set(order.getOrderId(),order);
        // todo 命令模式处理
        return true;
    }

    @OnTransition(source = "ORDER_WAIT_RECEIVE", target = "ORDER_FINISH")
    public boolean receiveToFinish(Message<OrderStateChangeAction> message) {
        Order order = (Order) message.getHeaders().get("order");
        if (order.getOrderState() != OrderState.ORDER_WAIT_RECEIVE) {
            throw new UnsupportedOperationException("order state error!");
        }
        order.setOrderState(OrderState.ORDER_FINISH);
        redisCommonProcessor.set(order.getOrderId(),order);
        // todo 命令模式处理
        return true;
    }
}
