package com.bowen.thread;

import java.util.concurrent.Executor;

public class MyExecutor implements Executor{

	@Override
	public void execute(Runnable command) {
		// TODO Auto-generated method stub
		command.run();
	}

}
