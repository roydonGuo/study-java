package com.roydon.flyweight;

/**
 * Client
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/17
 **/
public class Client {
    public static void main(String[] args) {
        AbstractBox boxI = BoxFactory.getInstance().getBox("I");
        boxI.display("白色");

        AbstractBox boxL = BoxFactory.getInstance().getBox("L");
        boxL.display("绿色");

        AbstractBox boxO1 = BoxFactory.getInstance().getBox("O");
        boxO1.display("蓝色");
        AbstractBox boxO2 = BoxFactory.getInstance().getBox("O");
        boxO2.display("红色");

        System.out.println("两次获取到的 O 是否为同一对象：" + (boxO1 == boxO2));
    }
}
