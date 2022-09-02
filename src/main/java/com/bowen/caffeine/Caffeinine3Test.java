package com.bowen.caffeine;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.*;
import java.util.function.BiFunction;

public class Caffeinine3Test {
    /**
     * 手动异步加载
     * 手动异步加载和手动加载类似，唯一的区别是这里的缓存加载是异步的。AsyncCache提供了在 Executor上生成缓存元素并返回 CompletableFuture的能力，synchronous()方法给 Cache提供了阻塞直到异步缓存生成完毕的能力。
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        // 手动异步加载
        AsyncCache<String, Object> cache3 = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(1000)
                .buildAsync();

       // 查找一个缓存元素， 没有查找到的时候返回null
        String key = "hello1";
        CompletableFuture<Object> cf = cache3.getIfPresent(key);
        System.out.println(cf);
        // 查找缓存元素，如果不存在，则异步生成
        cf = cache3.get(key, k -> createObject(key));
        System.out.println(cf);
        Object obj = cf.get();
        System.out.println(obj);



    }


    private static Object createObject(String key) {
        return "hello 2022";
    }
}
