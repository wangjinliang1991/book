package com.example.book.bridge.abst;

import com.example.book.bridge.function.RegisterLoginFuncInterface;
import com.example.book.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjl
 * @date 2024/8/10
 */
public abstract class AbstractRegisterLoginComponent {
    // 桥梁
    protected RegisterLoginFuncInterface funcInterface;
    public AbstractRegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
        validate(funcInterface);
        this.funcInterface = funcInterface;
    }

    protected final void validate(RegisterLoginFuncInterface funcInterface) {
        if (!(funcInterface instanceof RegisterLoginFuncInterface)) {
            throw new UnsupportedOperationException("unknown register/login function type!");
        }
    }

    protected abstract String login(String username, String password);
    protected abstract String register(UserInfo userInfo);
    protected abstract boolean checkUserExists(String userName);
    protected abstract String login3rd(HttpServletRequest request);

}
