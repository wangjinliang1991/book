package com.example.book.service.decorator;

import com.example.book.pojo.Order;
import com.example.book.service.OrderServiceInterface;

/**
 * @author wangjl
 * @date 2024/8/26
 */
public abstract class AbstractOrderServiceDecorator implements OrderServiceInterface {
        private OrderServiceInterface orderServiceInterface;

    public void setOrderServiceInterface(OrderServiceInterface orderServiceInterface) {
        this.orderServiceInterface = orderServiceInterface;
    }

    //覆写 createOrder方法
    @Override
    public Order createOrder(String productId) {
        return orderServiceInterface.createOrder(productId);
    }

    @Override
    public Order pay(String orderId) {
        return orderServiceInterface.pay(orderId);
    }

    @Override
    public Order send(String orderId) {
        return orderServiceInterface.send(orderId);
    }

    @Override
    public Order receive(String orderId) {
        return orderServiceInterface.receive(orderId);
    }

    @Override
    public String getPayUrl(String orderId, Float price, Integer payType) {
        return orderServiceInterface.getPayUrl(orderId, price, payType);
    }

    /**
     * 根据userId和productId更新用户积分、发放红包
     * @param productId 购买部分产品会有红包发放，部分产品无红包发放
     * @param serviceLevel 服务级别（正常服务，延迟服务，暂停服务
     * @param price 商品价格，根据商品价格的百分之一，为用户增加积分
     */
    protected abstract void updateScoreAndSendRedPaper(String productId, int serviceLevel,float price);

}
