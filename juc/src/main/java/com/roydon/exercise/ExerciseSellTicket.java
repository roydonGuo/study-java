package com.roydon.exercise;

import java.awt.image.RasterFormatException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ExerciseSell
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/7
 * 卖票问题
 **/
public class ExerciseSellTicket {

    private static final int PERSON_COUNT = 200; //买票人数
    private static final int TICKET_COUNT = 1000; //总票数

    static Random random = new Random();

    private static int randomTicketAmount() {
        // 随机1~10张票
        return random.nextInt(10) + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        // 买票窗口
        TicketWindow ticketWindow = new TicketWindow(TICKET_COUNT);

        List<Integer> amountList = new ArrayList<>();

        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < PERSON_COUNT; i++) {
            Thread thread = new Thread(() -> {
                amountList.add(ticketWindow.sell(randomTicketAmount()));
            });
            threadList.add(thread);
            thread.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        // 打印卖出票数
        System.out.println("卖出票数 = " + amountList.stream().mapToInt(a -> a).sum());
        // 统计剩余票数
        System.out.println("剩余票数 = " + ticketWindow.getTicketCount());

    }
}

class TicketWindow {
    private int ticketCount;

    public TicketWindow(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    // 卖票
    public synchronized int sell(int amount) {
        if (ticketCount >= amount) {
            ticketCount -= amount;
            return amount;
        }
        return 0;
    }
}
