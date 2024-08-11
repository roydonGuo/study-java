package com.roydon;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 几个立方体
        int x = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                x = Math.max(x, sc.nextInt());
            }
            list.add(x);
            x = 0;
        }
        Collections.sort(list, Collections.reverseOrder());
        list.forEach(System.out::print);

    }

}
