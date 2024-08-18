package com.example.book.items.visitor;

import com.example.book.items.composite.AbstractProductItem;

/**
 * @author wangjl
 * @date 2024/8/18
 */
public interface ItemVisitor<T> {
    T visitor(AbstractProductItem productItem);
}
