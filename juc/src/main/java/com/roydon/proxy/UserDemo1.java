package com.roydon.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * UserDemo1
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/8
 **/
public class UserDemo1 {
    public static void main(String[] args) {
        // 创建代理类
        UserService userService = new UserServiceImpl();
        InvocationHandler handler = new UserInvocationHandler(userService);
        UserService proxy = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                handler
        );
        proxy.addUser("roydon");

    }
}
