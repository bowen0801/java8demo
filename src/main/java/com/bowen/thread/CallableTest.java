package com.bowen.thread;

import org.junit.Test;

import java.util.concurrent.Callable;

public class CallableTest {

    @Test
    public void test(){
        /**
         * 添加阶段任务
         * @param pipe 任务
         * @param input 输入信息
         */
        /*protected void addTask(AbstractPipe pipe, IN input) {
            pipes.add(pipe);
            Callable<V> task = () -> (V) pipe.doProcess(input);
            tasks.add(task);
        }*/
        //批注：
        /**
         * 1.Callable 几乎可以和Runnable互换，只是Runnable run方法无返回值，callable的call方法可以返回值
         * 2.() -> (V) pipe.doProcess(input);？？
         */

    }
}
