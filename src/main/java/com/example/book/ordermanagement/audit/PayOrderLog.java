package com.example.book.ordermanagement.audit;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author wangjl
 * @date 2024/8/27
 */
@Component
public class PayOrderLog extends AbstractAuditLogProcessor{
    @Override
    protected OrderAuditLog buildDetails(OrderAuditLog auditLog) {
        HashMap<String, String> extraLog = new HashMap<>();
        extraLog.put("payType", "支付宝");
        extraLog.put("price", "100");
        auditLog.setDetails(extraLog);
        return auditLog;
    }
}
