package com.example.book.adapter;

/**
 * @author wangjl
 * @date 2024/8/10
 */
public interface Login3rdTarget {
    public String loginByGitee(String code,String state);

    public String loginByWechat(String ... params);

    public String loginByQQ(String ... params);
}
