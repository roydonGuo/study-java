package com.roydon;

import java.util.ArrayList;
import java.util.List;

/**
 * SHUMA
 *
 * @AUTHOR: roydon
 * @DATE: 2024/4/15
 **/
public class SHUMA {
    public static void main(String[] args) {
        List<List<Integer>> res = new ArrayList<>();
        int n = 5, m = 5;
        solution(n, m, res, 1, new ArrayList<>());
        for (List<Integer> r : res) {
            for (int i = 0; i < r.size(); i++) {
                if (i == r.size() - 1) {
                    System.out.println(r.get(i));
                } else {
                    System.out.print(r.get(i) + " ");
                }
            }
        }
    }

    public static void solution(int n, int m, List<List<Integer>> res, int begin, List<Integer> cur) {
        // 所有可能的组合，，
        if (m == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        // 遍历1-n
        for (int i = begin; i <= n && i <= m; i++) {
            cur.add(i);
            solution(n, m - i, res, i + 1, cur);
            cur.remove(cur.size() - 1);
        }
    }
}
