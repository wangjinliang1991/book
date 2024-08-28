package com.example.book.iterator;

/**
 * 抽象容器角色负责提供创建具体迭代器角色的抽象方法
 * @author wangjl
 * @date 2024/8/27
 */
public interface EsSqlQueryInterface<T>{
    public T iterator();
}
