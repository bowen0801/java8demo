package com.bowen.iterator;

import java.util.ArrayList;
import java.util.List;

public class MyIteratorTest {
    public static void main(String[] args) {
        List<MyUser> list = new ArrayList<>();
        FindService<MyUser> findService = new FindService<MyUser>();
        MyUser param = new MyUser();
        param.setAge(10);
        MyUser myUser = findService.find(param);



    }

}
