package com.example.book.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author wangjl
 * @date 2024/8/26
 */
@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, name = "product_id")
    private String productId;
    @Column(nullable = false, name = "send_red_bag")
    private int sendRedBag;
}
