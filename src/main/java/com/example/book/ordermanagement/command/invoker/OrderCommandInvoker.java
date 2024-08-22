package com.example.book.ordermanagement.command.invoker;

import com.example.book.ordermanagement.command.OrderCommandInterface;
import com.example.book.pojo.Order;

/**
 * @author wangjl
 * @date 2024/8/22
 */
public class OrderCommandInvoker {
    public void invoke(OrderCommandInterface command, Order order) {
        command.execute(order);
    }
}
