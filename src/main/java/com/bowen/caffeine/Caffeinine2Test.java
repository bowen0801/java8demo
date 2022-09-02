package com.bowen.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Caffeinine2Test {
    /**
     * 自动加载
     * 自动加载，顾名思义就是查不到数据时，系统会自动帮我们生成元素的缓存，只是这里构建的是LoadingCache，同时需要指定元素缓存的构造方法（也就是获取对象的方式，比如查库获取）
     * @param args
     */
    public static void main(String[] args) {
        LoadingCache<String,Object> cache = Caffeine.newBuilder().maximumSize(1000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build(Caffeinine2Test::createObject);
        /*Caffeine.newBuilder().maximumSize(1000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public @Nullable Object load(@NonNull String s) throws Exception {
                        return "hello caffeine 2021";
                    }
                });*/
        String key1 = "hello1";
        String key2 = "hello2";
        // 查找缓存，如果缓存不存在则生成缓存元素, 如果无法生成则返回null
        Object value = cache.get(key2);
        System.out.println(value);
        Object value1 = cache.get(key2);
        System.out.println(value1);

        Object val1 = cache.get(key1);
        System.out.println(val1);

       /* List<String> keys = new ArrayList<>();
        keys.add("hello1");
        keys.add("hello2");
        // 批量查找缓存，如果缓存不存在则生成缓存元素
        Map<String,Object> objectMap = cache.getAll(keys);
        System.out.println(objectMap);*/

    }

    private static Object createObject(String key){
        System.out.println("----createObject--key:"+key);
        if (key.equals("hello1")) {
            return "hello caffeine 2021";
        } else if (key.equals("hello2")){
            return "hello caffeine 2022";
        }
        return "11";
    }
}
