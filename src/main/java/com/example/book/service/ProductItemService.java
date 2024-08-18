package com.example.book.service;

import com.example.book.items.composite.AbstractProductItem;
import com.example.book.items.composite.ProductComposite;
import com.example.book.items.visitor.AddItemVisitor;
import com.example.book.items.visitor.DelItemVisitor;
import com.example.book.pojo.ProductItem;
import com.example.book.repo.ProductItemRepository;
import com.example.book.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangjl
 * @date 2024/8/14
 */
@Service
@Transactional
public class ProductItemService {
    @Autowired
    private RedisCommonProcessor redisProcessor;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private AddItemVisitor addItemVisitor;
    @Autowired
    private DelItemVisitor delItemVisitor;

    public ProductComposite fetchAllItems() {
        Object cacheItems = redisProcessor.get("items");
        if (cacheItems != null) {
            return (ProductComposite) cacheItems;
        }
        List<ProductItem> fetchDbItems = productItemRepository.findAll();
       ProductComposite items = generateProductTree(fetchDbItems);
        if (items == null) {
            throw new UnsupportedOperationException("product items should not be empty in db");
        }
        redisProcessor.set("items", items);
        return items;
    }

    //添加商品类目
    public ProductComposite addItems(ProductItem item) {
        //先更新数据库
        productItemRepository.addItem(item.getName(), item.getPid());
        //通过访问这模式访问树形结构，添加新的商品类目
        ProductComposite addItem = ProductComposite.builder()
                .id(productItemRepository.findByNameAndPid(item.getName(), item.getPid()).getId())
                .name(item.getName())
                .pid(item.getPid())
                .child(new ArrayList<>())
                .build();
        AbstractProductItem updateItems = addItemVisitor.visitor(addItem);
        return (ProductComposite) updateItems;
    }

    //删除商品类目
    public ProductComposite delItems(ProductItem item) {
        //先更新数据库
        productItemRepository.delItem(item.getId());
        ProductComposite delItem = ProductComposite.builder()
                .id(item.getId())
                .name(item.getName())
                .pid(item.getPid())
                .build();
        AbstractProductItem updateItems = delItemVisitor.visitor(delItem);
        //更新缓存 可设置重试机制，不成功人工介入
        redisProcessor.set("items",updateItems);
        return (ProductComposite) updateItems;
    }

    private ProductComposite generateProductTree(List<ProductItem> fetchDbItems) {
        List<ProductComposite> composites = new ArrayList<>(fetchDbItems.size());
        fetchDbItems.forEach(dbItem ->composites.add(ProductComposite.builder()
                .id(dbItem.getId())
                .name(dbItem.getName())
                .pid(dbItem.getPid()).build()));

        Map<Integer, List<ProductComposite>> groupingList = composites.stream().collect(Collectors.groupingBy(ProductComposite::getPid));
        composites.stream().forEach(item -> {
            List<ProductComposite> children = groupingList.get(item.getId());
            // 转换类型为 AbstractProductItem
            item.setChild(children == null ? new ArrayList<>() : children.stream().map(x -> (AbstractProductItem)x).collect(Collectors.toList()));
        });
        ProductComposite composite = composites.size() == 0 ? null : composites.get(0);
        return composite;
    }
}
