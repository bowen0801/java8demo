package com.bowen;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class ReturnTest {
    @Test
    public void testReturn(){
        //return可以跳出for循环
        List<String> nameList = Lists.newArrayList("bowen","Tom","hanmeimei");
        for (String s : nameList) {
            if (s.equals("Tom")) {
                return;
            }
            System.out.println(s);
        }
    }

    @Test
    public void testBreak(){
        //break可以跳出for循环
        List<String> nameList = Lists.newArrayList("bowen","Tom","hanmeimei");
        for (String s : nameList) {
            if (s.equals("hanmeimei")) {
                break;
            }
            System.out.println(s);
        }
    }
}
