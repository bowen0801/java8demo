package com.bowen.iterator;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class MyIterator<T> implements Iterator<T> {
    private Iterator<T> dataIterator;
    private Object param;
    private T item;

    public MyIterator(List<T> data, Object param) {
        this.dataIterator = data.iterator();
        this.param = param;
    }

    private boolean isMatch(T item) {
        if (this.param instanceof MyUser && item instanceof MyUser) {
            MyUser param = (MyUser) this.param;
            MyUser itemt = (MyUser) item;
            if (param.getAge() > itemt.getAge()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasNext() {
        if (this.item == null) {
            return false;
        }
        T tmp = null;
        while (dataIterator.hasNext()) {
            tmp = dataIterator.next();
            if (isMatch(tmp)) {
                this.item = tmp;
                break;
            }
        }
        return this.item != null;
    }

    @Override
    public T next() {
        T a = this.item;
        this.item = null;
        return a;
    }

}
