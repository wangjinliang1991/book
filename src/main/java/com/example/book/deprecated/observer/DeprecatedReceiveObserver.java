package com.example.book.deprecated.observer;

import com.example.book.deprecated.DeprecatedConstants;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wangjl
 * @date 2024/8/21
 */
@Component
public class DeprecatedReceiveObserver extends DeprecatedAbstractObserver{
    @PostConstruct
    public void init() {
        DeprecatedConstants.OBSERVER_LIST.add(this);
    }

    @Override
    public void orderStateHandle(String orderId, String orderState) {
        if (!orderState.equals("ORDER_FINISH")) {
            return;
        }
        System.out.println("监听到：订单创建成功，通过命令模式做后续处理");
    }
}
