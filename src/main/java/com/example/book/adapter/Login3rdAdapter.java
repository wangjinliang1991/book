package com.example.book.adapter;

import cn.hutool.json.JSONObject;
import com.example.book.pojo.UserInfo;
import com.example.book.service.UserService;
import com.example.book.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 类适配器 通过继承被适配的UserService类，进行扩展和复用
 * @author wangjl
 * @date 2024/8/10
 */
@Slf4j
@Component
public class Login3rdAdapter extends UserService implements Login3rdTarget{
    private static final String ACCESS_TOKEN = "access_token";
    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;

    @Override
    public String loginByGitee(String code, String state) {
        // state判断
        if (!giteeState.equals(state)) {
            throw new UnsupportedOperationException("Invalid state");
        }
        //请求gitee清台获取token，携带code
        String tokenUrl = giteeTokenUrl.concat(code);
        JSONObject tokenResponse = HttpClientUtils.execute(tokenUrl, HttpMethod.POST);
        String token = String.valueOf(tokenResponse.get(ACCESS_TOKEN));
        if (token == null) {
            throw new UnsupportedOperationException("Invalid token");
        }
        //请求用户信息，携带token
        String userUrl = giteeUserUrl.concat(token);
        JSONObject userInfoResponse = HttpClientUtils.execute(userUrl, HttpMethod.GET);
        log.info("userInfoResponse:{}", userInfoResponse);
        String userName = giteeUserPrefix.concat(String.valueOf(userInfoResponse.get("name")));
        String password = userName;
        return autoRegister3rdAndLogin(userName,password);
    }

    private String autoRegister3rdAndLogin(String userName, String password) {
        //如果登录过
        if (super.checkUserExists(userName)) {
            return super.login(userName, password);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(password);
        userInfo.setCreateDate(new Date());
        // 如果第一次登录，先进行自动注册
        super.register(userInfo);
        return super.login(userName, password);
    }

    @Override
    public String loginByWechat(String... params) {
        return null;
    }

    @Override
    public String loginByQQ(String... params) {
        return null;
    }
}
