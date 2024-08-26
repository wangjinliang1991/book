package com.example.book.repo;

import com.example.book.pojo.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangjl
 * @date 2024/8/26
 */
@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {
    // 根据product id 查询商品信息，JPA自动生成sql
    public Products findByProductId(String productId);
}
