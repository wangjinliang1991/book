package com.example.book.ordermanagement.audit;

import org.springframework.stereotype.Component;

/**
 * @author wangjl
 * @date 2024/8/27
 */
@Component
public class ReceiveOrderLog extends AbstractAuditLogProcessor{
    @Override
    protected OrderAuditLog buildDetails(OrderAuditLog auditLog) {
        return auditLog;
    }
}
