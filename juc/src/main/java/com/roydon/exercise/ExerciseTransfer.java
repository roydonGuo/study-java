package com.roydon.exercise;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ExerciseTransfer
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/8
 * 转账线程安全问题
 **/
public class ExerciseTransfer {

    private static final int TRANSFER_COUNT = 100; //转账操作次数
    private static final int TRANSFER_AMOUNT = 100; //转账金额区间

    static Random random = new Random();

    private static int randomTransferAmount() {
        return random.nextInt(TRANSFER_AMOUNT) + 1;
    }

    public static void main(String[] args) throws InterruptedException {

        Account accountA = new Account(1000);
        Account accountB = new Account(1000);

        // 1、单线程无并发问题
//        accountA.tranfer(accountB, randomTicketAmount());
//        System.out.println("账户A余额 = " + accountA.getMoney());
//        System.out.println("账户B余额 = " + accountB.getMoney());

        // 2、多线程账号互转
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < TRANSFER_COUNT; i++) {
                accountA.tranfer(accountB, randomTransferAmount());
            }
        });
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < TRANSFER_COUNT; i++) {
                accountB.tranfer(accountA, randomTransferAmount());
            }
        });

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();

        // 等待转账操作完成打印账户余额
        System.out.println("账户A余额 = " + accountA.getMoney() + "\n" + "账户B余额 = " + accountB.getMoney());


    }
}

class Account {
    private volatile int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * 转账方法
     *
     * @param tar    目标账户
     * @param amount 转账金额
     */
    public void tranfer(Account tar, int amount) {
        synchronized (Account.class) {
            if (this.money >= amount) {
                this.money -= amount;
                tar.setMoney(tar.money + amount);
            }
        }
    }

}
