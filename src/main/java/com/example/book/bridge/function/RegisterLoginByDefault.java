package com.example.book.bridge.function;

import com.example.book.pojo.UserInfo;
import com.example.book.repo.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author wangjl
 * @date 2024/8/10
 */
@Component
public class RegisterLoginByDefault implements RegisterLoginFuncInterface{
    @Autowired
    private UserRepository userRepository;
    @Override
    public String login(String account, String password) {
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
        if (userInfo == null) {
            return "account or password error";
        }
        return "login success";
    }

    @Override
    public String register(UserInfo userInfo) {
        if (checkUserExist(userInfo.getUserName())) {
            return "username already exist";
        }
        userInfo.setCreateDate(new Date());
        userRepository.save(userInfo);
        return "register success";
    }

    @Override
    public boolean checkUserExist(String userName) {
        UserInfo userInfo = userRepository.findByUserName(userName);
       return userInfo != null;
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        return null;
    }
}
