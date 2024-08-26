package com.example.book.service;

import com.example.book.pojo.Order;

/**
 * @author wangjl
 * @date 2024/8/26
 */
public interface OrderServiceInterface {
    Order createOrder(String productId);
    Order pay(String orderId);
    Order send(String orderId);
    Order receive(String orderId);
    String getPayUrl(String orderId, Float price, Integer payType);
}
