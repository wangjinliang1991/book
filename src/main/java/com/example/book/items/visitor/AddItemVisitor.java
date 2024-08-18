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
public class AddItemVisitor implements ItemVisitor<AbstractProductItem> {

    @Autowired
    private RedisCommonProcessor redisProcessor;
    @Override
    public AbstractProductItem visitor(AbstractProductItem productItem) {
        //从redis获取
        ProductComposite currentItem = (ProductComposite) redisProcessor.get("items");
        ProductComposite addItem = (ProductComposite) productItem;
        if (addItem.getPid() == currentItem.getId()) {
            currentItem.addProductItem(addItem);
            return currentItem;
        }
        addChild(addItem, currentItem);
        return currentItem;
    }

    public void addChild(ProductComposite addItem,ProductComposite currentItem) {
        for (AbstractProductItem abstractProductItem : currentItem.getChild()) {
            ProductComposite item = (ProductComposite) abstractProductItem;
            if (item.getId() == addItem.getPid()) {
                item.addProductItem(addItem);
                break;
            } else {
                addChild(addItem, item);
            }
        }
    }
}
