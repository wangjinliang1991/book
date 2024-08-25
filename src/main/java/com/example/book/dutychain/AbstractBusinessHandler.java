package com.example.book.dutychain;

import com.example.book.pojo.BusinessLaunch;

import java.util.List;

/**
 * @author wangjl
 * @date 2024/8/25
 */
public abstract class AbstractBusinessHandler {
    //定义下一个责任类
    public AbstractBusinessHandler nextHandler;

    //是否含有下一个责任类
    public boolean hasNextHandler() {
        return nextHandler != null;
    }

    //定义抽象责任类方法
    public abstract List<BusinessLaunch> processHandler(List<BusinessLaunch> launchList,
                                                        String targetCity, String targetSex,
                                                        String targetProduct);
}
