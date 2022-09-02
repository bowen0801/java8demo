package com.bowen.thread;

import java.util.concurrent.Callable;

public class ThreadCall implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println("=====");
        return "9999";
	}

}
