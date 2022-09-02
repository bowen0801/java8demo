package com.bowen.thread;

public class TheadTest {
	public static void main(String[] args) {
		// 创建、并启动第一条线程
        new FirstThread().start();
        // 创建、并启动第二条线程
        new FirstThread().start();
	}

}
