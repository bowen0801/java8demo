package com.bowen.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> ft = new FutureTask<>(new ThreadCall());
		new Thread(ft).start();
		System.out.println(ft.get());
	}

}
