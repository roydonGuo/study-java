package com.roydon.netty.n2;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * EventLoopTest
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/17
 **/
@Slf4j
public class EventLoopTest {
    public static void main(String[] args) {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup(2);
        System.out.println("loopGroup.next() = " + loopGroup.next());
        System.out.println("loopGroup.next() = " + loopGroup.next());
        System.out.println("loopGroup.next() = " + loopGroup.next());
        System.out.println("loopGroup.next() = " + loopGroup.next());

        // 1.提交普通任务
//        loopGroup.next().submit(()->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            log.debug("ok");
//        });
        // 2.提交定时任务
        loopGroup.next().scheduleAtFixedRate(() -> {
            log.debug("schedule task");
        }, 0, 1, TimeUnit.SECONDS);

        log.debug("main");
    }
}
