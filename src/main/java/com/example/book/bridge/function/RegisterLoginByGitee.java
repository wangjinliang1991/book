package com.example.book.bridge.function;

import cn.hutool.json.JSONObject;
import com.example.book.bridge.abst.factory.RegisterLoginComponentFactory;
import com.example.book.pojo.UserInfo;
import com.example.book.repo.UserRepository;
import com.example.book.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author wangjl
 * @date 2024/8/10
 */
@Slf4j
@Component
public class RegisterLoginByGitee extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface{

    @PostConstruct
    private void initFuncMap() {
        RegisterLoginComponentFactory.funcMap.put("GITEE", this);
    }

    private static final String ACCESS_TOKEN = "access_token";
    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String login3rd(HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        if (!giteeState.equals(state)) {
            throw new UnsupportedOperationException("invaild state");
        }
        //请求gitee清台获取token，携带code
        String tokenUrl = giteeTokenUrl.concat(code);
        JSONObject tokenResponse = HttpClientUtils.execute(tokenUrl, HttpMethod.POST);
        String token = String.valueOf(tokenResponse.get(ACCESS_TOKEN));
        if (token == null || token.equals("null")) {
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
        if (super.commonCheckUserExist(userName,userRepository)) {
            return super.commonLogin(userName, password,userRepository);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(password);
        userInfo.setCreateDate(new Date());
        // 如果第一次登录，先进行自动注册
        super.commonRegister(userInfo,userRepository);
        return super.commonLogin(userName, password,userRepository);
    }
}
