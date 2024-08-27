package com.example.book.service.decorator;

import com.example.book.ordermanagement.audit.PayOrderLog;
import com.example.book.pojo.Order;
import com.example.book.pojo.Products;
import com.example.book.repo.ProductsRepository;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author wangjl
 * @date 2024/8/26
 */
@Service
public class OrderServiceDecorator extends AbstractOrderServiceDecorator{
    //引入apollo配置中心的消息超时时间
    @Value("${delay.service.time}")
    private String delayServiceTime;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private PayOrderLog payOrderLog;

    @Override
    protected void updateScoreAndSendRedPaper(String productId, int serviceLevel, float price) {
        switch (serviceLevel){
            case 0:
                //0代表正常服务，直接进行积分更新和红包发放的逻辑判断
                // 根据价格的百分之一更新积分
                int score = Math.round(price) / 100;
                System.out.println("normal deal, update score:" + score + " for user");
                Products product = productsRepository.findByProductId(productId);
                if (product != null && product.getSendRedBag() == 1) {
                    System.out.println("normal deal, send red bag to user, productId = "+productId);
                }
                break;
            case 1:
                //1代表延迟服务，向队列中发送支持超时属性的消息
                MessageProperties properties = new MessageProperties();
                properties.setExpiration(delayServiceTime);
                Message msg = new Message(productId.getBytes(), properties);
                rabbitTemplate.send("normalExchange", "myRKey", msg);
                System.out.println("delay deal, time is: "+delayServiceTime);
                break;
            case 2:
                //2代表暂停服务，无其他逻辑
                System.out.println("pause service");
                break;
            default:
                throw new UnsupportedOperationException("unsupport service level");
        }
    }

    public Order decoratorPay(String orderId, int serviceLevel, float price) {
        //调用父类方法
        Order order = super.pay(orderId);
        // 保证不影响核心的支付逻辑
        try{
            updateScoreAndSendRedPaper(order.getProductId(), serviceLevel, price);
        }catch (Exception e){
            //处理异常
        }
        payOrderLog.createAuditLog("testAccount", "pay", orderId);
        return order;
    }
}
