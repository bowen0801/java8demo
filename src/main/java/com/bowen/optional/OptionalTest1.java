package com.bowen.optional;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

public class OptionalTest1 {
    @Test
    public void test1(){
        Student s = null;//new Student();
        //s.setGender("woman");
        String aNull = Optional.ofNullable(s).map(u -> u.getGender()).orElse("null");//消除if else
        System.out.println(aNull);
    }

    @Test
    public void test2(){
        List<String> list = Lists.newArrayList("book","tom","boat");
        Optional.ofNullable(list).ifPresent(todoTask->{
            todoTask.forEach(item->{
                System.out.println("item:"+item);
            });
        });
    }

    @Test
    public void test3(){
        String str = "";
        String s = Optional.ofNullable(null).orElse(-1).toString();
        System.out.println(s);
    }
    @Test
    public void testListNull(){
        //1.判断List集合是否为空
        List<String> resultList = new ArrayList<>();
        List<String> list = Lists.newArrayList("book","tom","boat");//null;//new ArrayList<>();//Lists.newArrayList("book","tom","boat");
        Optional.ofNullable(list).ifPresent(taskList -> resultList.addAll(taskList));
        resultList.forEach(item ->{
            System.out.println("== :"+item);
        });

        Optional.ofNullable(list).ifPresent(taskList -> resultList.addAll(taskList));
        resultList.forEach(item ->{
            System.out.println("-- :"+item);
        });


        Optional.ofNullable(list).ifPresent(taskList->{
            taskList.forEach(item->{
                System.out.println("vali item:"+item);
            });
        });

        Optional.ofNullable(list).ifPresent(taskList->{
            for (String s : taskList) {
                System.out.println("for:"+ s);
            }
        });

        Optional.ofNullable(list).ifPresent(taskList -> taskList.forEach(item ->{
            System.out.println("for list:"+ item);
        }));

    }

    @Test
    public void testMapNull(){
        Map<Long,String> map = new HashMap<>();
        for (String value : map.values()) {
            System.out.println("====");
        }
        //map.put(1L,"a");
       /* Optional.ofNullable(map).ifPresent( tmp ->{
            Set<Long> longs = map.keySet();
            System.out.println("---");
            for (Long aLong : longs) {
                System.out.println("key:"+aLong + " value:"+map.get(aLong));
            }
        });*/
    }

}
