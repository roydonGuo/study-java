package com.roydon.proxy.static_proxy;

/**
 * ProxyPoint
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/10
 **/
public class ProxyPoint implements SellTickets{

    // 静态代理要代理的类
    private TrainStation trainStation = new TrainStation();

    @Override
    public void sell() {
        System.out.println("代理商收费");
        trainStation.sell();
    }
}
