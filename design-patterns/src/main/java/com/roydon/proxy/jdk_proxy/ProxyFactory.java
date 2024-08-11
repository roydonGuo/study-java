package com.roydon.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ProxyFactory
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/10
 **/
public class ProxyFactory {
    private TrainStation trainStation = new TrainStation();

    public SellTickets getProxyObject() {
        SellTickets sellTickets = (SellTickets) Proxy.newProxyInstance(
                trainStation.getClass().getClassLoader(),
                trainStation.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("jdk动态代理");
                        Object invoke = method.invoke(trainStation, args);
                        return invoke;
                    }
                }
        );
        return sellTickets;
    }
}
