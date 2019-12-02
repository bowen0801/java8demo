package com.bowen.methodcallback.demo2;
/**
 * Caller :(调用者)
 * 调用者在访问被调用者的方法前.必须将回调接口对象设置给被调用者.以方便被调用者使用接口回调对象访问自己.
 * @since 2019年12月02日
 */
public class Test {
    public static void main(String[] args) {
        //1创建被调用者对象
        Callee callee = new Callee();
        //2.创建回调接口对象
        CallBackListener callBackListener = new CallBackListener() {
            @Override
            public void sendMessage2Caller(String msg) {
                //打印回调传递过来的结果
                System.out.println(msg);
            }
        };
        //3.必须将回调接口对象传递给被调用者
        callee.setCallBackListener(callBackListener);
        //4.访问被调用者的下载文件方法.这个方法完成后被调用者会通过回调接口对象通知我们.
        callee.DownLoadFile();
        System.out.println("你下载真慢,我先去忙别的事了....");
    }
}

