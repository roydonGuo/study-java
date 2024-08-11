package com.roydon.test;

public class Example {
    public static void main(String[] args) {
        int[][] arr = new int[4][4];
        arr[0][0] = 1;
        arr[0][1] = 2;
        arr[0][2] = 8;
        arr[0][3] = 9;
        arr[1][0] = 2;
        arr[1][1] = 4;
        arr[1][2] = 9;
        arr[1][3] = 12;
        arr[2][0] = 4;
        arr[2][1] = 7;
        arr[2][2] = 10;
        arr[2][3] = 13;
        arr[3][0] = 6;
        arr[3][1] = 8;
        arr[3][2] = 11;
        arr[3][3] = 15;
        System.out.println("check(arr,7) = " + check(arr, 7));
    }

    private static boolean check(int[][] arr, int target) {
        int l = arr[0].length;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < l; j++) {
                if (target == arr[i][j]) {
                    return true;
                } else if (target < arr[i][j]) {
                    l = j;
                    break;
                }
            }
        }
        return false;
    }
}
