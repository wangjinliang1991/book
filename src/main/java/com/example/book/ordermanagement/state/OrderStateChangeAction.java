package com.example.book.ordermanagement.state;

/**
 * @author wangjl
 * @date 2024/8/21
 */
public enum OrderStateChangeAction {
    PAY_ORDER, // 支付操作
    SEND_ORDER, // 发货操作
    RECEIVE_ORDER; //收货操作
}
