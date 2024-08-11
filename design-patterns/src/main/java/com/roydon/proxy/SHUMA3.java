package com.roydon.proxy;

import java.util.Scanner;

/**
 * SHUMA
 *
 * @AUTHOR: roydon
 * @DATE: 2024/4/15
 **/
public class SHUMA3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int x = in.nextInt();
        int xa = Math.abs(x-a);
        int xb = Math.abs(x-b);
        int xc = Math.abs(x-c);
        if(xa==xb&&xb==xc){
            System.out.println("same");
        }else {
            int min = Math.min(xa,Math.min(xb,xc));
            if(min==xa){
                System.out.println("A");
            }else if(min==xb){
                System.out.println("B");
            }else {
                System.out.println("C");
            }
        }
    }
}
