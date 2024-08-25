package com.example.book.pay.strategy;

import com.example.book.pojo.Order;

/**
 * @author wangjl
 * @date 2024/8/22
 */
public interface PayStrategyInterface {
    // 返回值类型string 访问第三方支付，平台返回一个url地址，让用户进行支付
    String pay(Order order);
}
