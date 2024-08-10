package com.example.book.bridge.function;

import com.example.book.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjl
 * @date 2024/8/10
 */
public interface RegisterLoginFuncInterface {
    public String login(String account,String password);
    public String register(UserInfo userInfo);
    public boolean checkUserExist(String userName);
    public String login3rd(HttpServletRequest request);
}
