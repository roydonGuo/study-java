package com.roydon.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * UserInvocationHandler
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/8
 **/
public class UserInvocationHandler implements InvocationHandler {

    private Object target;

    public UserInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before executing the method.");
        Object result = method.invoke(target, args);
        System.out.println("After executing the method.");
        return result;
    }
}
