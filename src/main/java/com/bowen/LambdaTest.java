package com.bowen;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * description
 *
 * @version v1.0.0
 * @since 2019年06月18日
 */

public class LambdaTest {


    public static void main(String[] args) {
        testForEach();
        testRunnable();

        List<String> cities = Arrays.asList("Milan",
                "London",
                "New York",
                "San Francisco");
        String citiesCommaSeparated = String.join(",", cities);
        System.out.println(citiesCommaSeparated);
        Collections.sort(cities, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

    }

    /**
     * 使用lambda表达式对列表进行迭代
     * @param
     */
    public static void testForEach(){
        // Java 8之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);

        }

        // Java 8之后：
        List<String> features2 = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));
    }

    @Test
    public void testForEach2(){
        //1 list.forEach中不能使用contine和break
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API","bowen");
        features.forEach( s ->{
            if (s.equals("bowen")) {//
            }
        });
    }

    /**
     * 用lambda表达式实现Runnable
     */
    public static void testRunnable(){
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



































}
