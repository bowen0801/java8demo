package com.bowen.methodcallback.demo22;
/**
 * 接口方法回调
 * @since 2019年12月02日
 */
public class Test {
    public static void main(String[] args) {
        //1.创建被调用者对象
        Callee callee = new Callee();
        //2.访问被调用者的下载文件方法.这个方法完成后被调用者会通过回调接口对象通知我们.
        //使用匿名内部类对象当做参数传递.更加简洁
        callee.DownLoadFile(new CallBackListener() {
            @Override
            public void sendMessage2Caller(String msg) {
                //打印回调传递过来的结果
                System.out.println(msg);
            }
        });
        System.out.println("你下载真慢,我先去忙别的事了...");
    }
}
