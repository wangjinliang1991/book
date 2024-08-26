package com.example.book.ticket.builder;

import com.example.book.ticket.product.PersonalTicket;

/**
 * @author wangjl
 * @date 2024/8/26
 */
public class PersonalTicketBuilder extends TicketBuilder<PersonalTicket> {
    //创建一个新的personalTicket对象
    private PersonalTicket personalTicket = new PersonalTicket();

    @Override
    public void setCommonInfo(String title, String product, String content) {
        personalTicket.setTitle(title);
        personalTicket.setProduct(product);
        personalTicket.setContent(content);
    }

    @Override
    public PersonalTicket buildTicket() {
        return personalTicket;
    }
}
