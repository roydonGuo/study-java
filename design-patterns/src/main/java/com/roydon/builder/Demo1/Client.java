package com.roydon.builder.Demo1;

/**
 * Client
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/10
 **/
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone.Builder()
                .cpu("cpu")
                .memory("m")
                .mainBoard("ma")
                .buttery("电池")
                .build();
        System.out.println(phone.toString());
    }
}
