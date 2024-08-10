package com.example.book.controller;

import com.example.book.adapter.Login3rdAdapter;
import com.example.book.pojo.UserInfo;
import com.example.book.service.UserBridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author wangjl
 * @date 2024/8/10
 */
@RestController
@RequestMapping("/bridge")
public class UserBridgeController {
    @Autowired
    private UserBridgeService userBridgeService;


    @PostMapping("/login")
    public String login(String account, String password) {
        return userBridgeService.login(account, password);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        return userBridgeService.register(userInfo);
    }

    // 参数调整为 HttpServletRequest
    @GetMapping("/gitee")
    public String gitee(HttpServletRequest request) throws IOException {
        return userBridgeService.login3rd(request,"GITEE");
    }
}
