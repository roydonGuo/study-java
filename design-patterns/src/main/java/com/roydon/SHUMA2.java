package com.roydon;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SHUMA
 *
 * @AUTHOR: roydon
 * @DATE: 2024/4/15
 **/
public class SHUMA2 {
    //    public static void main(String[] args) {
//        int n = 9, m = 6;
//        int[] price = new int[100];// {2, 3, 3, 6, 6, 6, 9, 9, 23};// 100
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        n = in.nextInt();
//        m = in.nextInt();
//        for (int i = 0; i < n; i++) {
//            price[i] = (in.nextInt());
//        }
//        int solution = solution(n, m, price);
//        System.out.println(solution);
//    }
//
//    public static int solution(int n, int m, int[] price) {
//        // n道菜，单价不超过m
//        Arrays.sort(price);
//        int count=0;
//        for (int i = 0; i < n; i++) {
//            if(price[i]==m&&price[i]<=m){
//                count++;
//            }else {
//                break;
//            }
//        }
//        return count;
//    }
    public static void main(String[] args) {
        int n = 9, m = 6;
        int[] price = new int[n];
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        n = in.nextInt();
        m = in.nextInt();
        for (int i = 0; i < n; i++) {
            price[i] = (in.nextInt());
        }
        int solution = solution(n, m, price);
        System.out.println(solution);
    }

    public static int solution(int n, int m, int[] price) {
        // n道菜，单价不超过m
        Arrays.sort(price);
        int index = 0;
        for (int i = 0; i < price.length; i++) {
            if (price[i] <= m) {
                index++;
            } else {
                break;
            }
        }
        index -= 1;
        int res = 0;
        for (int i = 0; i <= index; i++) {
            if (price[i] == price[index]) {
                res++;
            }
        }
        return res;
    }
}
