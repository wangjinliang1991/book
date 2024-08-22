package com.example.book.pojo;

import com.example.book.ordermanagement.state.OrderState;
import lombok.*;

/**
 * @author wangjl
 * @date 2024/8/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;
    private String productId;
    private OrderState orderState;
    private Float price;
}
