package com.roydon.spi.impl;

import com.roydon.spi.IShot;

/**
 * Cat
 *
 * @AUTHOR: roydon
 * @DATE: 2024/2/26
 **/
public class Cat implements IShot {
    @Override
    public void shot() {
        System.out.println("miao");
    }
}
