package com.roydon.combination;

/**
 * Client
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/17
 **/
public class Client {
    public static void main(String[] args) {
        // 创建菜单树
        MenuComponent menu1 = new Menu("菜单管理", 2);
        menu1.add(new MenuItem("页面访问", 3));
        menu1.add(new MenuItem("展开菜单", 3));
        menu1.add(new MenuItem("编辑菜单", 3));

        MenuComponent menu2 = new Menu("角色管理", 2);
        menu2.add(new MenuItem("添加角色", 3));
        menu2.add(new MenuItem("编辑角色", 3));

        // 创建一级菜单
        MenuComponent menu = new Menu("系统管理", 1);
        menu.add(menu1);
        menu.add(menu2);
        menu.print();

    }
}
