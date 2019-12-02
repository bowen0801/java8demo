package com.bowen.methodcallback.demo1;
import java.util.List;
/**
 * 方法回调测试
 * @since 2019年12月02日
 */
public class Test {
    public static void main(String[] args) {
        A a = new A();
        // 实现词库元素的删除
        a.execute(new CallBackInterface() {
            @Override
            public Object process(List param) {
                List<String> wordList = param;
                wordList.remove(1);
                return true;
            }
        });
        //实现词库元素的添加
        a.execute(new CallBackInterface() {
            @Override
            public Object process(List param) {
                List<String> wordList = param;
                wordList.add("24");
                return true;
            }
        });
        for(String s : a.wordList) {
            System.out.println(s);
        }
    }
}
