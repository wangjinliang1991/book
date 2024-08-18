package com.example.book.items.composite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Proc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangjl
 * @date 2024/8/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductComposite extends AbstractProductItem implements Serializable {
    private int id;
    private int pid;
    private String name;
    private List<AbstractProductItem> child = new ArrayList<>();

    @Override
    public void addProductItem(AbstractProductItem item) {
        this.child.add(item);
    }

    @Override
    public void delProductChild(AbstractProductItem item) {
        ProductComposite removeItem = (ProductComposite) item;
        Iterator<AbstractProductItem> iterator = child.iterator();
        while (iterator.hasNext()) {
            ProductComposite composite = (ProductComposite) iterator.next();
            // 移除ID相同的商品类目
            if (composite.getId() == removeItem.getId()) {
                iterator.remove();
                break;
            }
        }
    }
}
