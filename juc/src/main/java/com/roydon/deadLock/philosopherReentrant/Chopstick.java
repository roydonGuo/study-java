package com.roydon.deadLock.philosopherReentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Chopstick
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/9
 **/
public class Chopstick extends ReentrantLock {

    private String name;

    public Chopstick(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
