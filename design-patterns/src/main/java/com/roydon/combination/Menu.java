package com.roydon.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/17
 **/
public class Menu extends MenuComponent{

    private List<MenuComponent> menuComponentList = new ArrayList<>();

    public Menu(String name,int level){
        this.level = level;
        this.name = name;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponentList.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponentList.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return menuComponentList.get(i);
    }

    @Override
    public void print() {

        for (int i = 1; i < level; i++) {
            System.out.print("|--");
        }
        System.out.println(name);
        for (MenuComponent menuComponent : menuComponentList) {
            menuComponent.print();
        }
    }

}
