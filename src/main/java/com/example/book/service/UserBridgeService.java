package com.example.book.service;

import com.example.book.bridge.abst.AbstractRegisterLoginComponent;
import com.example.book.bridge.abst.RegisterLoginComponent;
import com.example.book.bridge.abst.factory.RegisterLoginComponentFactory;
import com.example.book.bridge.function.RegisterLoginByDefault;
import com.example.book.pojo.UserInfo;
import com.example.book.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author wangjl
 * @date 2024/8/10
 */
@Service
public class UserBridgeService {

    public String login(String account, String password) {
        AbstractRegisterLoginComponent registerLoginComponent = RegisterLoginComponentFactory.getComponent("Default");
       return registerLoginComponent.login(account, password);
    }

    public String register(UserInfo userInfo) {
        AbstractRegisterLoginComponent registerLoginComponent = RegisterLoginComponentFactory.getComponent("Default");
        return registerLoginComponent.register(userInfo);
    }

    public String login3rd(HttpServletRequest request,String type) {
        AbstractRegisterLoginComponent registerLoginComponent = RegisterLoginComponentFactory.getComponent(type);
        return registerLoginComponent.login3rd(request);
    }

}
