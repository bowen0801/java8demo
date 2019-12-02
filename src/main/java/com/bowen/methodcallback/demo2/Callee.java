package com.bowen.methodcallback.demo2;
/**
 * Callee(被调用者):
 * 1.被调用者类中必须有回调接口类型的变量.
 * 2.并且还要有给该变量赋值的setXxx()方法.
 * 3.在可以给调用者返回结果的时候.拿着接口类型的变量调用接口的方法进行对调用者的访问.(因为接口类型的变量要执行的方法在调用者那里存在).
 * @since 2019年12月02日
 */
public class Callee {
    //1.被调用者类中必须有回调接口类型的变量
    private CallBackListener callBackListener;
    //2.并且还要有给该变量赋值的setxxx方法
    public void setCallBackListener(CallBackListener callBackListener) {
        this.callBackListener = callBackListener;
    }
    /**
     * 下载文件是非常耗时的工作.而调用者又需要在下载完成后读取下载的文件.
     * 所以当这个方法完成下载后应该立刻通知调用者.这个通知的动作就称之为回调.
     * @throws InterruptedException
     */
    public void DownLoadFile() {
        //开启新的线程去下载，异步任务
        new Thread(){
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                //使用睡眠模拟耗时工作.
                try{
                    Thread.sleep(5000);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                long end = System.currentTimeMillis();
                //完成下载后通知调用者
                if (callBackListener != null) {
                    callBackListener.sendMessage2Caller("下载完成,耗时:"+(end-start)/1000+"秒,你可以去访问资源了.");
                }
            }
        }.start();
    }
}
