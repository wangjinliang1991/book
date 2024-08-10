package com.example.book.bridge.abst.factory;

import com.example.book.bridge.abst.AbstractRegisterLoginComponent;
import com.example.book.bridge.abst.RegisterLoginComponent;
import com.example.book.bridge.function.RegisterLoginFuncInterface;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjl
 * @date 2024/8/10
 */
public class RegisterLoginComponentFactory {
    public static Map<String, AbstractRegisterLoginComponent> componentMap = new ConcurrentHashMap<>();
    //缓存不通类型的实现类，右路,如 RegisterLoginByDefault,RegisterLoginByGitee
    public static Map<String, RegisterLoginFuncInterface> funcMap = new ConcurrentHashMap<>();

    // 根据不同的登录类型，获取AbstractRegisterLoginComponent
    public static AbstractRegisterLoginComponent getComponent(String type) {
        AbstractRegisterLoginComponent component = componentMap.get(type);
        //并发情况，双重检查锁
        if (component == null) {
            synchronized (componentMap) {
                component = componentMap.get(type);
                if (component == null) {
                    component = new RegisterLoginComponent(funcMap.get(type));
                    componentMap.put(type, component);
                }
            }
        }
        return component;
    }
}
