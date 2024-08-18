package com.example.book.items.composite;

/**
 * @author wangjl
 * @date 2024/8/14
 */
public abstract class AbstractProductItem {
    //增加商品类目
    protected void addProductItem(AbstractProductItem item) {
        throw new UnsupportedOperationException("not support child add!");
    }

    //移除商品类目
    protected void delProductChild(AbstractProductItem item) {
        throw new UnsupportedOperationException("not support child remove");
    }
}
