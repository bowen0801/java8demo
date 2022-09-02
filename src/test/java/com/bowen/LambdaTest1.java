package com.bowen;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * description
 * @version v1.0.0
 * @since 2019年06月18日
 */
public class LambdaTest1 {


    /**
     * 使用lambda表达式对列表List进行迭代
     * @param
     */
    @Test
    public  void testForEach(){
        // Java 8之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }

        // Java 8之后：
        List<String> features2 = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

        List<String> list = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        list.forEach(string ->{
            System.out.println(string);
        });
        // 以上n 与 string 都是局部变量
    }
    /**
     * lambda表达式遍历的list为null时也会报空指针异常
     */
    @Test
    public void listEmptyList() {
        List<String> list = new ArrayList<String>();
        list.forEach((n)->{
            System.out.println("result:"+n);
        });
    }

    /**
     * 遍历Map集合
     */
    @Test
    public void listMap() {
        Map<String, Integer> items = new HashMap<String,Integer>();
        items.put("java", 10);
        items.put("php", 20);
        items.put("python", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);
        //1
        items.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));
        System.out.println("-------------------------------------------");
        //2
        items.forEach((key,value)->{
            System.out.println("Item : " + key + " Count : " + value);
        });
    }

    /**
     * 用lambda表达式对List实现排序
     */
    @Test
    public void testSort() {
        List<String> list = Arrays.asList("a","c","d","b");
        // java8之前匿名类写法
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for(String str:list) {
            System.out.println(str);
        }
        System.out.println("-------------------");
        // java8labmda表达式方式
        List<String> list1 = Arrays.asList("a","c","d","b");
        Collections.sort(list1,(o1,o2)->o1.compareTo(o2));
        for(String str:list1) {
            System.out.println(str);
        }
    }

    /**
     * 用lambda表达式实现Runnable
     */
    @Test
    public  void testRunnable(){
        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        //Java 8方式：
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
    }

    @Test
    public void testList() {
        try{

        }catch(Exception ex){

        }
    }

    /**
     * List转String
     */
    @Test
    public void testListToString(){
        List<Long> pharmacyIdList = new ArrayList<>();
        pharmacyIdList.add(100L);
        pharmacyIdList.add(200L);
        pharmacyIdList.add(300L);

        String collect = pharmacyIdList.stream().map(s -> s.toString()).collect(Collectors.joining(","));
        System.out.println(collect);
    }








}
