package com.example.book.ticket.builder;

import com.example.book.ticket.product.CompanyTicket;

/**
 * @author wangjl
 * @date 2024/8/26
 */
public class CompanyTicketBuilder extends TicketBuilder<CompanyTicket> {
    private CompanyTicket companyTicket = new CompanyTicket();
    @Override
    public void setBankInfo(String bankInfo) {
        companyTicket.setBankInfo(bankInfo);
    }

    @Override
    public void setTaxId(String taxId) {
        companyTicket.setTaxId(taxId);
    }

    @Override
    public void setCommonInfo(String title, String product, String content) {
        companyTicket.setTitle(title);
        companyTicket.setProduct(product);
        companyTicket.setContent(content);
    }

    @Override
    public CompanyTicket buildTicket() {
        return companyTicket;
    }
}
