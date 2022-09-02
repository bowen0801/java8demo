package com.bowen.caffeine;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Caffeinine4Test {
    /**
     * 自动异步加载
     *自动异步加载和自动加载对应，只是这里的加载是异步的，和手动异步加载一样，当然因为是自动加载，所以需要我们指定缓存加载方法。默认情况下，采用的线程池也是ForkJoinPool.commonPool()，另外自动异步加载也支持自定义线程池类型
     * @param args
     */
    public static void main(String[] args) {
        // 自动异步加载
        AsyncLoadingCache<String, Object> cache4 = Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                // 你可以选择: 去异步的封装一段同步操作来生成缓存元素
                .buildAsync(key -> createObject(key));
                // 你也可以选择: 构建一个异步缓存元素操作并返回一个future
                // .buildAsync((key, executor) -> createExpensiveGraphAsync(key, executor));
        // 查找缓存元素，如果其不存在，将会异步进行生成
        String key = "hello";
        CompletableFuture<Object> object4 = cache4.get(key);
        // 批量查找缓存元素，如果其不存在，将会异步进行生成
        List<String> keys = new ArrayList<>();
        keys.add("hello1");
        keys.add("hello2");
        CompletableFuture<Map<String, Object>> graphs = cache4.getAll(keys);
        
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
