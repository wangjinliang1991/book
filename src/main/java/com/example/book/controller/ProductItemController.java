package com.example.book.controller;

import com.example.book.items.composite.ProductComposite;
import com.example.book.pojo.ProductItem;
import com.example.book.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjl
 * @date 2024/8/14
 */
@RestController
@RequestMapping("/product")
public class ProductItemController {
    @Autowired
    private ProductItemService productItemService;

    @PostMapping("/fetchAllItems")
    public ProductComposite fetchAllItems() {
        return productItemService.fetchAllItems();
    }

    //添加商品类目的接口
    @PostMapping("/addItems")
    public ProductComposite addItems(@RequestBody ProductItem item) {
        return productItemService.addItems(item);
    }

    //删除商品类目
    @PostMapping("/delItems")
    public ProductComposite delItems(@RequestBody ProductItem item) {
        return productItemService.delItems(item);
    }
}
