package com.example.book.service;

import com.example.book.pojo.UserInfo;
import com.example.book.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wangjl
 * @date 2024/8/10
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


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

    private boolean checkUserExists(String userName) {
        UserInfo user = userRepository.findByUserName(userName);
        return user != null;
    }


}
