package com.bowen;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");

        List<String> list2 = new ArrayList<>();
        list.addAll(list2);
        System.out.println(list.size());
        for (String s : list) {
            System.out.println("name:"+s);
        }

        List<String> list3 = new ArrayList<>();
        for (String s : list3) {
            System.out.println(s);
        }


    }
}
