package com.bowen.methodcallback.demo22;
/**
 * 下载文件是非常耗时的工作.而调用者又需要在下载完成后读取下载的文件.
 * * 所以当这个方法完成下载后应该立刻通知调用者.这个通知的动作就称之为回调.
 * * final CallBackListener cb 匿名内部类访问局部变量必须加final.
 * @since 2019年12月02日
 */
public class Callee {
    public void DownLoadFile(final CallBackListener cb){
        //开启新的线程去下载.异步任务.
        new Thread(){
            public void run() {
                long start = System.currentTimeMillis();

                //使用睡眠模拟耗时工作.
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long end = System.currentTimeMillis();

                //完成下载后通知调用者
                if(cb != null){
                    cb.sendMessage2Caller("下载完成,耗时:"+(end-start)/1000+"秒,你可以去访问资源了.");
                }

            };
        }.start();
    }
}
