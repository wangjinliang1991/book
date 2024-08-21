package com.example.book.deprecated.observer;

/**
 * @author wangjl
 * @date 2024/8/21
 */
public abstract class DeprecatedAbstractObserver {
    //订单发声状态变更，调用该方法
    public abstract void orderStateHandle(String orderId, String orderState);

}
