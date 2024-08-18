package com.example.book.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author wangjl
 * @date 2024/8/14
 */
@Data
@Entity
@Table(name = "product_item")
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int pid;
}
