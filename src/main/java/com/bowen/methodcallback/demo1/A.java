package com.bowen.methodcallback.demo1;
import java.util.ArrayList;
import java.util.List;
/**
 * @since 2019年12月02日
 */
public class A {
    public List<String> wordList = loadList();

    public <T> T execute(CallBackInterface callBackInterface) {
        //可以先执行一些execute的逻辑
        //直接将相关对词库的操作权交给callbackInterface
        T result = (T)callBackInterface.process(wordList);
        return result;
    }
    //加载词库到内存
    public List<String> loadList() {
        List<String> wordList = new ArrayList<String>();
        for(int i = 0; i < 10;i++) {
            wordList.add(Integer.toString(i));
        }
        return wordList;
    }
}
