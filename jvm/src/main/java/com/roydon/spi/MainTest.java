package com.roydon.spi;

import java.util.ServiceLoader;

/**
 * MainTest
 *
 * @AUTHOR: roydon
 * @DATE: 2024/2/26
 **/
public class MainTest {
    public static void main(String[] args) {
        ServiceLoader<IShot> load = ServiceLoader.load(IShot.class);
        for (IShot iShot : load) {
            iShot.shot();
        }
    }
}
