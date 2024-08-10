package com.example.book.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author wangjl
 * @date 2024/8/10
 */
@Data
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String userPassword;
    @Column(nullable = false)
    private Date createDate;
    @Column
    private String userEmail;
}
