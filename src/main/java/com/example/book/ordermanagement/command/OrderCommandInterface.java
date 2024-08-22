package com.example.book.ordermanagement.command;

import com.example.book.pojo.Order;

/**
 * @author wangjl
 * @date 2024/8/22
 */
public interface OrderCommandInterface {
    void execute(Order order);
}
