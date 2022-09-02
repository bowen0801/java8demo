package com.bowen.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class Caffinine1Test {
    /**
     *
     * Caffeine提供了四种缓存添加策略
     * 手动加载
     * 自动加载
     * 手动异步加载
     * 自动异步加载
     */
    public static void main(String[] args) {
        Cache<String,Object> cache = Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).maximumSize(100).build();
        // 查找一个缓存元素， 没有查找到的时候返回null
        String key = "a";
        Object object = cache.getIfPresent(key);
        System.out.println(object);

        // 查找缓存，如果缓存不存在则生成缓存元素, 如果无法生成则返回null
        String key1 = "hello1";
        String key2 = "hello2";
        object = cache.get(key1, k -> createObject(key1));
        System.out.println(object);
        object = cache.get(key1, k -> createObject(key1));
        System.out.println(object);

        object = cache.get(key2, k -> createObject(key2));
        System.out.println(object);

        /*// 添加或者更新一个缓存元素
        cache.put(key, "obj2011");//key已存在则覆盖操作
        System.out.println(cache.getIfPresent(key));

        // 移除一个缓存元素
        cache.invalidate(key);
        System.out.println(cache);
        System.out.println(cache.getIfPresent(key));*/
    }

    private static Object createObject(String key) {
        System.out.println("----createObject--key:"+key);
        if (key.equals("hello1")) {
            return "hello caffeine 2021";
        } else if (key.equals("hello2")){
            return "hello caffeine 2022";
        }
        return "11";
    }
}
