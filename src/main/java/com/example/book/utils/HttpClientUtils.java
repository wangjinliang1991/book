package com.example.book.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.http.HttpMethod;

/**
 * @author wangjl
 * @date 2024/8/10
 */
public class HttpClientUtils {
    public static JSONObject execute(String url, HttpMethod httpMethod) {
        HttpResponse response = null;
        if (httpMethod == HttpMethod.GET) {
            response = HttpRequest.get(url).execute();
        } else {
            response = HttpRequest.post(url).execute();
        }
        String body = response.body();
        response.close();
        return JSONUtil.parseObj(body);
    }
}
