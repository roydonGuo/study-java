package com.roydon.adapter.object_adapter;

public class Client {
    public static void main(String[] args) {
        Computer computer = new Computer();
        System.out.println(computer.readSD(new SDCardImpl()));

        System.out.println("------------");

        System.out.println(computer.readSD(new SDAdapterTF(new TFCardImpl())));
    }
}
