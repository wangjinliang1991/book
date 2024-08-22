package com.example.book.deprecated.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjl
 * @date 2024/8/18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;

    private String productId;

    private String state;
}
