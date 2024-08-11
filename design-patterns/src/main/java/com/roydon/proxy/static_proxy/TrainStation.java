package com.roydon.proxy.static_proxy;

/**
 * TrainStation
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/10
 **/
public class TrainStation implements SellTickets{
    @Override
    public void sell() {
        System.out.println("火车站卖出了一张票");
    }
}
