package com.bowen.thread;

public class MyExecutorTest {
	public static void main(String[] args) {
		new MyExecutor().execute(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});
	}

}
