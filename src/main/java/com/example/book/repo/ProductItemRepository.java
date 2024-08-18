package com.example.book.repo;

import com.example.book.pojo.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author wangjl
 * @date 2024/8/14
 */
@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {
    //添加  jda的自定义接口
    @Modifying
    @Query(value = "INSERT INTO PRODUCT_ITEM (id,name,pid)" +
            "values ((select max(id)+1 from PRODUCT_ITEM),?1,?2)",nativeQuery = true)
    public void addItem(String name,int pid);

    //删除商品类目和直接子目录
    @Modifying
    @Query(value = "DELETE FROM PRODUCT_ITEM WHERE " +
            "id = ?1 or pid=?1", nativeQuery = true)
    public void delItem(int id);

    public ProductItem findByNameAndPid(String name, int pid);
}
