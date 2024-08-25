package com.example.book.controller;

import com.example.book.adapter.Login3rdAdapter;
import com.example.book.pojo.BusinessLaunch;
import com.example.book.pojo.UserInfo;
import com.example.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

/**
 * @author wangjl
 * @date 2024/8/10
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Login3rdAdapter login3rdAdapter;


    @PostMapping("/login")
    public String login(String account, String password) {
        return userService.login(account, password);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        return userService.register(userInfo);
    }

    @GetMapping("/gitee")
    public String gitee(String code,String state) throws IOException {
        return login3rdAdapter.loginByGitee(code, state);
    }

    @PostMapping("/business/launch")
    public List<BusinessLaunch> filterBusinessLaunch(@RequestParam("city") String city, @RequestParam("sex") String sex,
                                                     @RequestParam("product") String product) {
        return userService.filterBusinessLaunch(city,sex,product);
    }
}
