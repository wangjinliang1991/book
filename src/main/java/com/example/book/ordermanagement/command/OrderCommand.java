package com.example.book.ordermanagement.command;

import com.example.book.ordermanagement.command.receiver.OrderCommandReceiver;
import com.example.book.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangjl
 * @date 2024/8/22
 */
@Component
public class OrderCommand implements OrderCommandInterface{
    @Autowired
    private OrderCommandReceiver receiver;
    @Override
    public void execute(Order order) {
        this.receiver.action(order);
    }
}
