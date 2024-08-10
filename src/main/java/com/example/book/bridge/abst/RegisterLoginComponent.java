package com.example.book.bridge.abst;

import com.example.book.bridge.function.RegisterLoginFuncInterface;
import com.example.book.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjl
 * @date 2024/8/10
 */
public class RegisterLoginComponent extends AbstractRegisterLoginComponent{

    public RegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
        super(funcInterface);
    }

    @Override
    public String login(String username, String password) {
        return funcInterface.login(username, password);
    }

    @Override
    public String register(UserInfo userInfo) {
        return funcInterface.register(userInfo);
    }

    @Override
    public boolean checkUserExists(String userName) {
        return funcInterface.checkUserExist(userName);
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        return funcInterface.login3rd(request);
    }
}
