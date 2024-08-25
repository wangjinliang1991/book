package com.example.book.service;

import com.example.book.dutychain.AbstractBusinessHandler;
import com.example.book.dutychain.CityHandler;
import com.example.book.dutychain.builder.HandlerEnum;
import com.example.book.pojo.BusinessLaunch;
import com.example.book.pojo.UserInfo;
import com.example.book.repo.BusinessLaunchRepository;
import com.example.book.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author wangjl
 * @date 2024/8/10
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessLaunchRepository businessLaunchRepository;
    @Value("${duty.chain}")
    private String handlerType;
    //记录当前handlerType的配置，判断duty.chain配置是否发生改变
    private String currentHandlerType;
    //记录责任链头节点，没有配置更改，下次直接返回
    private AbstractBusinessHandler currentHandler;


    public String login(String account, String password) {
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
        if (userInfo == null) {
            return "account/password ERROR!";
        }
        return "Login Success";
    }

    public String register(UserInfo userInfo) {
        if (checkUserExists(userInfo.getUserName())) {
            throw new RuntimeException("user already registered.");
        }
        userInfo.setCreateDate(new Date());
        userRepository.save(userInfo);
        return "register success";
    }

    public boolean checkUserExists(String userName) {
        UserInfo user = userRepository.findByUserName(userName);
        return user != null;
    }

    public List<BusinessLaunch> filterBusinessLaunch(String city, String sex, String product) {
        List<BusinessLaunch> launchList = businessLaunchRepository.findAll();
        return buildChain().processHandler(launchList, city, sex, product);
    }

    private AbstractBusinessHandler buildChain() {
        // 如果没有配置，直接返回null
        if (handlerType == null) {
            return null;
        }
        //如果第一次配置，将handlerType记录下来
        if (currentHandlerType == null) {
            currentHandlerType = handlerType;
        }
        //说明duty.chain的配置并未修改切currentHandler不为null，直接返回currentHandler
        if (currentHandlerType.equals(handlerType) && currentHandler != null) {
            return currentHandler;
        } else {
            //说明duty.chain的配置有更改，需要重新初始化责任链条，保证线程安全，只有每次修改才能执行一次此处的代码
            System.out.println("config has changed or firstly initialized, compile the chain");
            synchronized (this) {
                try {
                    AbstractBusinessHandler dummyHeadHandler = new CityHandler();
                    //创建前置节点，初始赋值为哑节点
                    AbstractBusinessHandler preHandler = dummyHeadHandler;
                    List<String> handlerTypeList = Arrays.asList(handlerType.split(","));
                    for (String handlerType : handlerTypeList) {
                        //创建当前节点
                        AbstractBusinessHandler handler = (AbstractBusinessHandler) Class.forName(HandlerEnum.valueOf(handlerType).getValue()).newInstance();
                        preHandler.nextHandler = handler;
                        preHandler = handler;
                    }
                    //重新赋值新的责任链头节点
                    this.currentHandler = dummyHeadHandler.nextHandler;
                    this.currentHandlerType = this.handlerType;
                    return currentHandler;
                } catch (Exception e) {
                    throw new UnsupportedOperationException(e);
                }
            }
        }
    }
}
