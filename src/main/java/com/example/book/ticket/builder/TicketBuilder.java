package com.example.book.ticket.builder;

/**
 * @author wangjl
 * @date 2024/8/26
 */
public abstract class TicketBuilder<T> {
    //设置通用发票信息
    public abstract void setCommonInfo(String title,String product, String content);

    //设置企业税号
    public  void setTaxId(String taxId) {
        throw new UnsupportedOperationException();
    }

    //设置企业银行卡信息
    public  void setBankInfo(String bankInfo) {
        throw new UnsupportedOperationException();
    }

    //抽象构造方法
    public abstract T buildTicket();
}
