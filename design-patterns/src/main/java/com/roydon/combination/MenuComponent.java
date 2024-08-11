package com.roydon.combination;

import lombok.Getter;

/**
 * MenuComponent
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/17
 * 菜单组件  不管是菜单还是菜单项，都应该继承该类
 **/
public abstract class MenuComponent {

    //获取菜单名称
    @Getter
    protected String name;
    protected int level;

    //添加菜单
    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    //移除菜单
    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    //获取指定的子菜单
    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    public void print() {
        throw new UnsupportedOperationException();
    }

}
