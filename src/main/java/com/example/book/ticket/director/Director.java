package com.example.book.ticket.director;

import com.example.book.ticket.builder.CompanyTicketBuilder;
import com.example.book.ticket.builder.PersonalTicketBuilder;
import org.springframework.stereotype.Component;

/**
 * @author wangjl
 * @date 2024/8/26
 */
@Component
public class Director extends AbstractDirector{
    @Override
    public Object buildTicket(String type, String product, String content, String title, String bankInfo, String taxId) {
        if (type.equals("person")) {
            PersonalTicketBuilder personalTicketBuilder = new PersonalTicketBuilder();
            personalTicketBuilder.setCommonInfo(title,product,content);
            return personalTicketBuilder.buildTicket();
        }else if (type.equals("company")) {
            CompanyTicketBuilder companyTicketBuilder = new CompanyTicketBuilder();
            companyTicketBuilder.setCommonInfo(title,product,content);
            companyTicketBuilder.setBankInfo(bankInfo);
            companyTicketBuilder.setTaxId(taxId);
            return companyTicketBuilder.buildTicket();
        }
        throw new RuntimeException("ticket type error");
    }
}
