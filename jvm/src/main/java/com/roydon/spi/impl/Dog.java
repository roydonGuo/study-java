package com.roydon.spi.impl;

import com.roydon.spi.IShot;

/**
 * Dog
 *
 * @AUTHOR: roydon
 * @DATE: 2024/2/26
 **/
public class Dog implements IShot {
    @Override
    public void shot() {
        System.out.println("wang");
    }
}
