package com.bowen.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CompleteFutureTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
		System.out.println("time:"+(System.currentTimeMillis() - start));
		List<String> list = new ArrayList<String>();
		CompletableFuture<String> f1 = CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(1000*1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "a";
		}, threadPoolExecutor);
		
		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(1000*10);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "b";
		}, threadPoolExecutor);
		list.add(f1.get());
		list.add(f2.get());
		
		for (String string : list) {
			System.out.println(string);
		}
		if (threadPoolExecutor != null) {
			threadPoolExecutor.shutdown();
		}
	
		
	}
	
	

}
