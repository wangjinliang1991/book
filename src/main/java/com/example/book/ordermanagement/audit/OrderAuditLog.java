package com.example.book.ordermanagement.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wangjl
 * @date 2024/8/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderAuditLog {
    //当前用户信息
    private String account;
    // 用户操作
    private String action;
    // 用户操作具体时间
    private Date date;
    private String orderId;
    // 其他额外信息
    private Object details;
}
