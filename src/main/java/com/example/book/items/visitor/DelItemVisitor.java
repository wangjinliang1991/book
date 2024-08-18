package com.example.book.items.visitor;

import com.example.book.items.composite.AbstractProductItem;
import com.example.book.items.composite.ProductComposite;
import com.example.book.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangjl
 * @date 2024/8/18
 */
@Component
public class DelItemVisitor implements ItemVisitor<AbstractProductItem>{
    @Autowired
    private RedisCommonProcessor redisProcessor;

    @Override
    public AbstractProductItem visitor(AbstractProductItem productItem) {
        ProductComposite currentItem = (ProductComposite) redisProcessor.get("items");
        // 待删除的
        ProductComposite delItem = (ProductComposite) productItem;
        if (delItem.getId() == currentItem.getId()) {
            throw new UnsupportedOperationException("根节点不能删");
        }
        if (delItem.getPid() == currentItem.getId()) {
            currentItem.delProductChild(delItem);
            return currentItem;
        }
        delChild(delItem, currentItem);
        return currentItem;
    }

    private void delChild(ProductComposite delItem, ProductComposite currentItem) {
        //递归
        for (AbstractProductItem abstractProductItem : currentItem.getChild()) {
            ProductComposite item = (ProductComposite) abstractProductItem;
            if (item.getId() == delItem.getPid()) {
                item.delProductChild(delItem);
                break;
            } else {
                delChild(delItem,item);
            }
        }
    }
}
