package com.roydon.combination;

/**
 * MenuItem
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/17
 **/
public class MenuItem extends MenuComponent {

    public MenuItem(String name,int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void print() {
        for (int i = 1; i < level; i++) {
            System.out.print("|--");
        }
        System.out.println(name);
    }
}
