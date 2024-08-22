package com.example.book.ordermanagement.statementmachine;

import com.example.book.ordermanagement.state.OrderState;
import com.example.book.ordermanagement.state.OrderStateChangeAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.redis.RedisStateMachinePersister;

import java.util.EnumSet;

/**
 * @author wangjl
 * @date 2024/8/21
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine") //开启状态机
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderStateChangeAction> {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderStateChangeAction> states) throws Exception {
        // 创建成功后的初始状态为待支付
        states.withStates().initial(OrderState.ORDER_WAIT_PAY)
                // orderState 所有状态，加载配置到状态机
                .states(EnumSet.allOf(OrderState.class));
    }

    // 状态转化配置类
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderStateChangeAction> transitions) throws Exception {
        transitions.withExternal()
                .source(OrderState.ORDER_WAIT_PAY)
                .target(OrderState.ORDER_WAIT_SEND)
                .event(OrderStateChangeAction.PAY_ORDER)
                .and()
                .withExternal()
                .source(OrderState.ORDER_WAIT_SEND)
                .target(OrderState.ORDER_WAIT_RECEIVE)
                .event(OrderStateChangeAction.SEND_ORDER)
                .and()
                .withExternal()
                .source(OrderState.ORDER_WAIT_RECEIVE)
                .target(OrderState.ORDER_FINISH)
                .event(OrderStateChangeAction.RECEIVE_ORDER);
    }

    @Bean(name = "stateMachineRedisPersister")
    public RedisStateMachinePersister<OrderState, OrderStateChangeAction> getRedisPersister() {
        // StateMachine存储的dao层
        RedisStateMachineContextRepository<OrderState,OrderStateChangeAction> repository
                = new RedisStateMachineContextRepository<>(redisConnectionFactory);
        // dao层的封装
        RepositoryStateMachinePersist persist = new RepositoryStateMachinePersist<>(repository);
        // 返回工具类RedisStateMachinePersister
        return new RedisStateMachinePersister<>(persist);
    }
}
