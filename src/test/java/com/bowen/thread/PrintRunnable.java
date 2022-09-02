package com.bowen.thread;

public class PrintRunnable implements Runnable {

	@Override
	public void run() {
		int i = 1;
        while(i<=10){
        	System.out.println(Thread.currentThread().getName() + "正在运行!"+(i++));
        }
	}

}
