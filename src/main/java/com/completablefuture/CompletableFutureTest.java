package com.completablefuture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {
    public static void main(String[] args) throws JsonProcessingException {
        //分模块异步查询
        long beginTime = System.currentTimeMillis();
        ResultData result = new ResultData();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //任务一查询用户信息
        CompletableFuture<UserVo> f1 = CompletableFuture.supplyAsync(() -> {
            UserVo vo = new UserVo().setId(1L).setName("bowen");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return vo;
        }, executorService);
        //任务二查询车型信息
        CompletableFuture<CarVo> f2 = CompletableFuture.supplyAsync(() -> {
            CarVo vo = new CarVo().setNo(2L).setCarName("吉利");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return vo;
        }, executorService);
        //组装查询信息
        result.setUser(f1.join()).setCar(f2.join());

        long endTime = System.currentTimeMillis();
        System.out.println("耗时："+(endTime-beginTime) +" ms");

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(result));


    }
}
