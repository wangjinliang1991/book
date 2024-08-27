package com.example.book.ordermanagement.audit;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author wangjl
 * @date 2024/8/27
 */
@Component
public class SendOrderLog extends AbstractAuditLogProcessor{
    @Override
    protected OrderAuditLog buildDetails(OrderAuditLog auditLog) {
        HashMap<String, String> extraLog = new HashMap<>();
        extraLog.put("快递公司", "菜鸟");
        extraLog.put("快递编号", "10010111");
        auditLog.setDetails(extraLog);
        return auditLog;
    }
}
