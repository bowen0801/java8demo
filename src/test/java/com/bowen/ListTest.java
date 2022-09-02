package com.bowen;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    @Test
    public void testRemoveIf(){

        List<String> list = Lists.newArrayList("a","b","c");
        list.removeIf(item ->{
            boolean flag = false;
            if (item.equals("b")) {
                flag = true;
            }
            return flag;
        });
        list.forEach(item ->{
            System.out.println(item);
        });
    }
}
