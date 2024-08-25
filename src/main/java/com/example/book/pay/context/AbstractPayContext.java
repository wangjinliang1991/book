package com.example.book.pay.context;

import com.example.book.pojo.Order;

/**
 * 抽象产品类
 * @author wangjl
 * @date 2024/8/23
 */
public abstract class AbstractPayContext {
    public abstract String execute(Order order);
}
