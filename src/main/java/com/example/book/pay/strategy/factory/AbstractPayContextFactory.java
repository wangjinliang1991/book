package com.example.book.pay.strategy.factory;

/**
 * 抽象工厂类 扩展新的具体工厂子类时才能发挥作用
 * @author wangjl
 * @date 2024/8/23
 */
public abstract class AbstractPayContextFactory<T> {
    public abstract T getContext(Integer payType);
}
