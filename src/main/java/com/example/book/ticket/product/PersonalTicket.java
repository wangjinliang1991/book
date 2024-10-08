package com.example.book.ticket.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 个人发票
 * @author wangjl
 * @date 2024/8/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalTicket implements Cloneable{
    //发票固定不变的信息
    private String finalInfo;
    //发票抬头
    private String title;
    //商品信息
    private String product;
    //税率 发票代码 校验吗 收款方等
    private String content;

    @Override
    public PersonalTicket clone()  {
        PersonalTicket personalTicket = null;
        try {
            personalTicket = (PersonalTicket) super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return personalTicket;
    }
}
