package com.example.book.ticket.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangjl
 * @date 2024/8/27
 */
@Component
public class DirectorProxy extends AbstractDirector{
    @Autowired
    private Director director;

    @Override
    public Object buildTicket(String type, String productId, String content, String title, String bankInfo, String taxId) {
        //前置处理: 通过productId获取商品信息
        String product = this.getProduct(productId);

        if (bankInfo != null && !this.validateBankInfo(bankInfo)) {
            return null;
        }

        return director.buildTicket(type, product, content, title, bankInfo, taxId);
    }

    private boolean validateBankInfo(String bankInfo) {
        System.out.println("validate bankInfo ...");
        return true;
    }

    private String getProduct(String productId) {
        return "通过productId获取商品信息";
    }
}
