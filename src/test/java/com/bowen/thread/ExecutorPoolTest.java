package com.bowen.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorPoolTest {
	public static void main(String[] args) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			newFixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" test");
				}
			});
		}
		System.out.println(newFixedThreadPool);
		newFixedThreadPool.shutdown();
		System.out.println(newFixedThreadPool.isTerminated());
		System.out.println(newFixedThreadPool.isShutdown());
	}

}
