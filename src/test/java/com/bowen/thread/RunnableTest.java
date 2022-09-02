package com.bowen.thread;

public class RunnableTest {
	public static void main(String[] args) {
		PrintRunnable pr = new PrintRunnable();
        Thread t1 = new Thread(pr);
        t1.start();
        //PrintRunnable pr1 = new PrintRunnable();
        Thread t2 = new Thread(pr);
        t2.start();
	}

}
