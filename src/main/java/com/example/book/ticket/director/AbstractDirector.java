package com.example.book.ticket.director;

/**
 * @author wangjl
 * @date 2024/8/26
 */
public abstract class AbstractDirector {
    public abstract Object buildTicket(String type, String product,String content,String title,
                                       String bankInfo,String taxId);
}
