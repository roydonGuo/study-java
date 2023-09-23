package com.roydon;

import java.io.IOException;
import java.util.ArrayList;

/**
 * MATDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/5
 **/
public class MATDemo {
    public static void main(String[] args) throws IOException {
        ArrayList<Object> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add(1);
        System.out.println(1);
        System.in.read();

        list = null;
        System.out.println(2);
        System.in.read();
        System.out.println("end");
    }

}
