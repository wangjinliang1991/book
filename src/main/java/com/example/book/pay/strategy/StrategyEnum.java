package com.example.book.pay.strategy;

/**
 * 枚举类，封装具体的策略类，避免暴露给工厂
 * @author wangjl
 * @date 2024/8/24
 */
public enum StrategyEnum {
    alipay("com.example.book.pay.strategy.AlipayStrategy"),
    wechat("com.example.book.pay.strategy.WechatStrategy");
    String value = "";

    StrategyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
