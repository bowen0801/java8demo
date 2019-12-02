package com.bowen.methodcallback.demo1;
import java.util.List;
/**
 * @since 2019年12月02日
 */
public interface CallBackInterface<T> {
    T process(List<Object> param);
}
