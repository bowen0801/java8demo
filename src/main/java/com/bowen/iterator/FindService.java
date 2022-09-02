package com.bowen.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindService<T> {
    private List<T> data;

    public Iterator<T> iterator(Object param){
        return new MyIterator<>(this.data,param);
    }

    public T find(Object param){
        Iterator<T> it = this.iterator(param);
        if (it.hasNext()) {
            return it.next();
        } else {
            return null;
        }
    }

    List<T> findAll(Object param){
       List<T> list = new ArrayList<>();
       Iterator<T> it = this.iterator(param);
       while (it.hasNext()) {
           list.add(it.next());
       }
       return list;
    }


}
