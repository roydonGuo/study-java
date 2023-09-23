//package com.roydon.waitnotify;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j(topic = "com.demo.WaitNotifyAllDemo1")
//public class WaitNotifyAllDemo1 {
//    static final Object room = new Object();
//    static boolean hasCigarette = false;    //有没有烟
//    static boolean hasTakeout = false;
//
//    public static void main(String[] args) throws InterruptedException {
//        new Thread(() -> {
//            synchronized (room) {
//                log.debug("有烟没？[{}]", hasCigarette);
//                while (!hasCigarette) {//while防止虚假唤醒
//                    log.debug("没烟，先歇会！");
//                    try {
//                        room.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                log.debug("有烟没？[{}]", hasCigarette);
//                if (hasCigarette) {
//                    log.debug("可以开始干活了");
//                } else {
//                    log.debug("没干成活...");
//                }
//            }
//        }, "小南").start();
//
//        new Thread(() -> {
//            synchronized (room) {
//                Thread thread = Thread.currentThread();
//                log.debug("外卖送到没？[{}]", hasTakeout);
//                if (!hasTakeout) {
//                    log.debug("没外卖，先歇会！");
//                    try {
//                        room.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                log.debug("外卖送到没？[{}]", hasTakeout);
//                if (hasTakeout) {
//                    log.debug("可以开始干活了");
//                } else {
//                    log.debug("没干成活...");
//                }
//            }
//        }, "小女").start();
//
//
//        Thread.sleep(1000);
//        new Thread(() -> {
//        // 这里能不能加 synchronized (room)？
//            synchronized (room) {
//                hasTakeout = true;
//				//log.debug("烟到了噢！");
//                log.debug("外卖到了噢！");
//                room.notifyAll();
//            }
//        }, "送外卖的").start();
//    }
//}
