package com.bowen.methodcallback.demo22;
/**
 * CallBackListener (回调监听接口)
 * 用于回调的接口 (接口内的抽象方法用于监听被调用者返回的结果)
 * 接口中有回调方：该回调方法是被调用者通过回调接口对象去访问调用者的方法.所以形参应该是被调用者返回给调用者结果的数据类型和参数个数。
 * @since 2019年12月02日
 */
public interface CallBackListener {
    //发送消息给调用者
    public abstract void sendMessage2Caller(String msg);
}
