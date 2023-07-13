package com.chain;

public class ChainTest {
    public static void main(String[] args) {
        //链式调用
        User u = new User().setId(1L).setName("博文");
        System.out.println(u.getId()+ " " +u.getName());

    }
}
