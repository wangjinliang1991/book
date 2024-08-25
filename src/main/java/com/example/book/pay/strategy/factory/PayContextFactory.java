package com.example.book.pay.strategy.factory;

import com.example.book.pay.context.PayContext;
import com.example.book.pay.strategy.PayStrategyInterface;
import com.example.book.pay.strategy.StrategyEnum;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjl
 * @date 2024/8/23
 */
@Component
public class PayContextFactory extends AbstractPayContextFactory<PayContext>{
    //创建 map 数据结构作为缓存 存储 payContext
    private static final Map<String, PayContext> payContexts = new ConcurrentHashMap<>();

    @Override
    public PayContext getContext(Integer payType) {
        //根据payType定位枚举类
        StrategyEnum strategyEnum =
                payType == 1 ? StrategyEnum.alipay :
                        payType == 2? StrategyEnum.wechat :
                                null;
        if (strategyEnum == null) {
            throw new UnsupportedOperationException("payType not supported");
        }
        // 从map中获取 payContext
        PayContext context = payContexts.get(strategyEnum.name());
        // 第一次调用
        if (context == null) {
            try {
                //反射创建
                PayStrategyInterface payStrategy = (PayStrategyInterface) Class.forName(strategyEnum.getValue()).newInstance();
                PayContext payContext = new PayContext(payStrategy);
                payContexts.put(strategyEnum.name(), payContext);
            } catch (Exception e) {
                throw new UnsupportedOperationException("get payStrategy failed");
            }

        }
        return payContexts.get(strategyEnum.name());
    }


}
