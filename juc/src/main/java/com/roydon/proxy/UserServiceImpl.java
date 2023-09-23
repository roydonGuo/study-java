package com.roydon.proxy;

/**
 * UserServiceImpl
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/8
 **/
public class UserServiceImpl implements UserService{
    @Override
    public void addUser(String username) {
        System.out.println("username = " + username);
    }
}
