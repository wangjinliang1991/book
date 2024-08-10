package com.example.book.bridge.function;

import com.example.book.pojo.UserInfo;
import com.example.book.repo.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author wangjl
 * @date 2024/8/10
 */
public abstract class AbstractRegisterLoginFunc implements RegisterLoginFuncInterface{
    protected String commonLogin(String account, String password, UserRepository userRepository) {
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
        if (userInfo == null) {
            return "account or password error";
        }
        return "login success";
    }

    protected String commonRegister(UserInfo userInfo, UserRepository userRepository) {
        if (commonCheckUserExist(userInfo.getUserName(),userRepository)) {
            return "username already exist";
        }
        userInfo.setCreateDate(new Date());
        userRepository.save(userInfo);
        return "register success";
    }

    protected boolean commonCheckUserExist(String userName, UserRepository userRepository) {
        return userRepository.findByUserName(userName) != null;
    }

    public String login(String account,String password) {
        throw new UnsupportedOperationException();
    }
    public String register(UserInfo userInfo) {
        throw new UnsupportedOperationException();
    }
    public boolean checkUserExist(String userName) {
        throw new UnsupportedOperationException();
    }
    public String login3rd(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }

}
